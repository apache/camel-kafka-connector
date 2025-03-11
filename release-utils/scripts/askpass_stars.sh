#!/bin/bash
#
# askpass_stars [options] [prompt]
#
# Originally this was a shell script to "ask password with stars".  Now it is
# primarily a wrapper around the "systemd-ask-password" command (if
# available), with a fall back to the shell script version (see end of script).
# It also includes as simple bash no-echo read, for non-TTY use.
#
# There are also certain problems with "systemd-ask-password", especially in
# older versions, which this script takes care of transparently.
#
# For more information about how this script originally came about see...
#    https://antofthy.gitlab.io/info/crypto/passwd_askpass_stars.txt
#
# Options:
#   --help     This document
#   -b|--bash     Just use the simple bash read (prompt, no echo, and no stars)
#   -s|--stars    Force the use of the shell 'askpass_stars' loop, not systemd
#   -d|--debug    In shell script version, echo the character codes recieved
#
# Password Caching Options (if available)...
#
# These options allows a password to be cached into the Linux Kernel Keyring,
# for a period of time, so that it can be retrieved and used again later.
# Again this is a area where "systemd-ask-password" has some problems.
#
#   --timeout={sec}  Timeout for cached password. Default is 1800s or 30 mins
#   --retrieve={key} Retrieve password from {key} if present.
#                    NOTE: This will NEVER store a user typed password.
#
#   --keyname={key}  Cache a password (if asked) in {key} for a time period
#   --accept-cached  Used with "--keyname" to try to retrieve and/or store.
#                    For compatibility with "systemd-ask-password".
#                    Do not use this if user is creating a new password!
#   --clear-cached   Don't ask for a password, just clear the given -keyname
#
# Caching the password is especially useful when editing encrypted files.
# You would use "--keyname" when getting the password to decrypt a file, so as
# to cached it, for a limited period of time.  Later when the file is being
# written would use "--retrieve", to use the original password to re-encrypt
# the file, or have the user type it again, twice (no caching).
#
# These two options prevents user accidentally changing the password of
# a encrypted file they are editing.  But if the cache has timed out the user
# will be asked for the password again (twice), without the password being
# cached.
#
# To be compatible with "systemd-ask-password", the options "--accept-cached"
# with "--keyname" have been provided.  However this can not be used when you
# need to read the password twice.  The the first time it asks it will cache
# the password and not ask a second time.  Thus, using "--accept-cached" for
# editing an encrypted file is not a good idea as it means if a new password is
# requested when writing an encrypted file, it will be cached, preventing the
# password from being asked twice!  That is why the "--retrieve=" option has
# been provided to retrieve the password if available, and never cache if the
# password is typed in.
#
# The "--clear-cached" option will invalidate and clear the cached password,
# that is no longer required, rather than waiting for it to timeout.  For
# example when an editing session is now finished, and the cached password is
# no longer needed.
#
# This script can be used on non-linux systems. But no password caching will be
# used, and all key management commands are disabled (no errors reported) The
# user will then always be asked for the password when re-encrypting the file.
#
# NOTE: "systemd-ask-password" defaults to caching the password for 2-1/2
# minutes.  Though that is good for quick commands that may be repeated, such
# as a encrypted file lookup, or "sudo" like situations, it is not long enough
# for file editing situations.
#
# For more information on password caching see my notes in
#    https://antofthy.gitlab.io/info/crypto/passwd_caching.txt
#
# Downloading Latest Version of this script...
#
# The latest version of this script can be downloaded from...
#       https://antofthy.gitlab.io/software/#askpass_stars
#
###
#
# KNOWN BUGS:
#
# * When reading a password (shell loop using stars) interrupts are handled
#   simply, to allow the script to restore TTY settings.  Ideally the script
#   should flag the interrupt, and kill the bash 'read' so as to break the
#   input reading loop.  That has proved to be harder than it sounds, and why
#   the "systemd-ask-password" reader has been preferred when available.
#
# * The "systemd-ask-password" command can leave the TTY in a bad state when
#   interrupted (such as when used by "sudo". To solve this the script wraps
#   it in some code to ensure the TTY is restored correctly when it exits.
#   See "https://antofthy.gitlab.io/info/crypto/passwd_input.txt" for detail.
#
# * The "systemd-ask-password" prefixes an emoji 'padlock' to the prompt.
#   This script disables that addition (if possible) as such characters are
#   not available on all terminals.
#
# * Using this script with "sudo -A" via the "$SUDO_ASKPASS" environment
#   variable on RH6, results in a 'zombie' process for the duration of the
#   "sudo" command.  This is because "sudo -A" fails to wait on the password
#   reading child process.  The zombie gets cleaned up when 'sudo" exits.
#
###
#
# Version 1 - Original Script to "ask passwords with stars from terminal"
#    See  http://www.ict.griffith.edu.au/anthony/info/crypto/passwd_input.txt
# Version 2 - Addition of "systemd-ask-password" wrapper
# Version 3.0 - Password Caching added
# Version 3.1 - Addition of a password cache timeout option
# Version 3.2 - Add a timeout option for cache time period
# Version 4.0 - Vim ModifyOtherKeys Bug and Miscellanious Improvments
# Version 4.1 - Slight improvements for security
#
# Anthony Thyssen <Anthony.Thyssen@gmail.com> -- Version 4,  January 2023
#
###
#
# Discover where the shell script resides
PROGNAME="${BASH_SOURCE##*/}"      # script name
PROGDIR="${BASH_SOURCE%/*}"        # extract directory of program

Usage() {  # Report error and Synopsis line only
  echo >&2 "$PROGNAME:" "$@"
  sed >&2 -n '1,2d; /^###/q; /^#/!q; /^#$/q; s/^#  */Usage: /p;' \
          "$PROGDIR/$PROGNAME"
  echo >&2 "For help use  $PROGNAME --help"
  exit 10;
}
Help() {   # Output Full header comments as documentation
  sed >&2 -n '1d; /^###/q; /^#/!q; s/^#//; s/^ //; p' \
          "$PROGDIR/$PROGNAME"
  exit 10;
}
Error() {  # Just output an error condition and exit (no usage)
  echo >&2 "$PROGNAME:" "$@"
  exit 2
}

# set path
export PATH="/usr/bin:/bin"
# Note, for Solaris UNIX, we will also need /usr/ucb/stty.
HTYPE=`uname | tr -d - | tr '[A-Z]' '[a-z]'`
[[ "$HTYPE" = 'sunos' ]] && PATH="/usr/ucb:$PATH"

# Ensure this is not a environment variable that has been set externally
unset PWORD; PWORD=

#ask_stars=true     # force the use of the ask_stars loop, (not systemd)
#bash_read=true     # just simply read using bash "read" (noecho prompt)
#debug=true         # Turn on debugging

#key_timeout=150    # 2-1/2 minutes (default as per "systemd-ask-password")
#key_timeout=300    # short 5 minute timeout (as per "sudo" timeout)
key_timeout=3200    # cache password in "--keyname" for 1 hour (editing)

while [  $# -gt 0 ]; do
  case "$1" in

  # Standard help option.
  -\?|-help|--help|--doc*) Help ;;

  -s|--stars)   ask_stars=true ;;  # force use of the ask_stars loop
  -b|--bash)    bash_read=true ;;  # read password using BASH password read
  -d|--debug)   debug=true ;;      # debugging

  # Use Linux Kernel Key Management to cache the password.
  # Though it is often better to have calling program to key management.
  # My encryption script "keepout" does KM itself, but "encrypt" does not.
  --keyname*)   keyname=$(expr + "$1" : '--keyname=\(..*\)') ||
                        { shift; keyname="$1"; } ;;                # store
  --retrieve*)  retrieve=$(expr + "$1" : '--retrieve=\(..*\)') ||
                        { shift; retrieve="$1"; } ;;               # retrieve
  --timeout*)   key_timeout=$(expr + "$1" : '--timeout=\(..*\)') ||
                        { shift; key_timeout="$1"; } ;;            # timeout
  --accept-cached) accept_cache=true ;;   # use cache or ask password
  --clear-cached)  clear_cache=true ;;    # clear any cached key and exit

  --) shift; break ;;    # forced end of user options
  -*) Usage "Unknown option \"$1\"" ;;
  *)  break ;;           # unforced  end of user options

  esac
  shift   # next option
done

prompt="${*:-Password:}" # any other argument is the prompt to use
prompt="${prompt% }"     # remove any final space in prompt
prompt="${prompt% }"     #    systemd-ask-password adds a space!
prompt="${prompt% }"     #    so we will add a space later for consistancy

# Key storage settings
if [ "$accept_cache" ]; then
  [ "X$keyname" = "X" ] && Usage "Missing keyname for '--accept-cached'"
  [ "$clear_cache" ] &&
     Usage "Options '--accept-cached' and '--clear-cached' mutually exclusive"
  retrieve="$keyname"    # systemd compatibility option
fi
[ "$clear_cache" ] &&
  [ "X$keyname" = "X" ] && Usage "Missing keyname for '--clear-cached'"

# WARNING:
# Vim-8.2 is using a XTerm ANSI "ModifyOtherKeys" key sequence to allow it to
# better see shift/control/alt Function keys.  But in some versions this mode
# does not get disabled when running the command via vim 'autocmd'.
#
# If this is happening, you will see multiple stars appear for each keypress
# instead of a single star per key (star sequence is 3 to 4 times longer!)
# when using vim to edit a encrypted file requiring passwords.
#
# If this becomes a problem the following code will disable the ModifyOtherKeys
# mode of the ANSI terminal during password reading.
#
# if tty >/dev/null; then
#   #echo >/dev/tty "Fixing XTerm ANSI 'ModifyOtherKeys'..."
#   case "$TERM" in
#     xterm*) echo -n $'\e[>4n' >/dev/tty ;;
#   esac
# fi

# -----------------------------------------------------------------------------
# Retrieve a password from cache (if present) and return it without prompt
if [ "X$retrieve" != "X" ]; then
  if key_id=$(keyctl request user "$retrieve" 2>/dev/null); then
    [[ "$debug" ]] && echo 2>&1 "DEBUG: retrieve password from keyring cache."
    # Use "pipe" as "print" could print in hex if password is binary!
    if keyctl pipe "$key_id" 2>/dev/null; then
      echo ""     # add a newline to the end of the retrieved password
      # refresh the timeout for retrieved keys
      keyctl timeout "$key_id" $key_timeout 2>/dev/null
      exit 0
    fi
  fi
  # password was not cached -- fallthru and prompt the user for one
fi

# Purge any previous password stored in the key!
#
# Note that "systemd-ask-password" appends the new password to a previously
# cached password, with a NULL separator.  This ensures any previous cached
# password is cleared to prevent this unwanted behaviour.
if [ "X$keyname" != "X" ]; then
  [[ "$debug" ]] && echo 2>&1 "DEBUG: Purge password from keyring cache."
  keyctl purge user "$keyname" >/dev/null 2>&1
  # NB: 'purge' always returns a 0 exit status
  # regardless of if the key was present or not

  # exit if that is all we were asked to do
  [ "$clear_cache" ] && exit 0;
fi

# -----------------------------------------------------------------------------
# There is no TTY!
#
# Just read one line and output it (SHORT-CIRCUIT).
# This is done using a simple BASH 'read', but the password will still be
# cached, if options given to do so.
#
if [ "$bash_read" ] || ! tty >/dev/null; then
  [[ "$debug" ]] && echo 2>&1 "DEBUG: Simple BASH password read."

  # Bash automatically disables the prompt if the input is not a TTY
  read -r -s -p "$prompt " PWORD
  tty >/dev/null && echo >&2 ""
  echo "$PWORD"

  # Cache the password into the linux kernel keyring (set timeout)...
  if [ "X$keyname" != "X" ]; then
    [[ "$debug" ]] && echo 2>&1 "DEBUG: Cache password (bash read)."
    if key_id=$(echo -n "$PWORD" |
                keyctl padd user "$keyname" @u 2>/dev/null); then
      keyctl timeout "$key_id" $key_timeout 2>/dev/null
    fi
  fi
  exit 0
fi

# -----------------------------------------------------------------------------
# Use the systemd ask password program (if available)

# If the "systemd-ask-password" binary version is available, and we are not
# forcing the use of the 'echo stars' loop, then use the binary version.
# This way this script does not itself handle the password, just fix
# the caching timeout if needed.

# As we already know we are on a TTY then this program will prompt
# the user on the current TTY, and not go to a 'agent'.
#
if [ -z "$ask_stars" ] && [ -x /bin/systemd-ask-password ]; then
  # Use the systemd version - without a timeout
  [[ "$debug" ]] && echo 2>&1 "DEBUG: Systemd Ask password Read."
  #
  # Warning: If "sudo" calls this script, and user interrupts it, then it can
  # leave the TTY in a bad state!  As such I save and restore the TTY settings
  # to prevent this from happening.
  #
  stty_save=$(stty -g)
  trap 'stty "$stty_save"' EXIT
  sysd_options=( '--timeout=0' )  # don't timeout password input.
  # Can we turn off the 'padlock' Unicode character prefix (optional)
  systemd-ask-password --help | grep -q -- '--emoji=' &&
    sysd_options+=( '--emoji=no' )  # disable 'padlock' prefix
  sysd_options+=( ${keyname:+"--keyname=$keyname"} )

  # Now ask password, with stars!
  /bin/systemd-ask-password "${sysd_options[@]}" -- "$prompt"
  exitval=$?

  # Reset the key timeout on the password cached by the above command.
  # Normally "systemd-ask-password" sets it to 2-1/2 minutes!
  if [ $exitval -eq 0 ] && [ "X$keyname" != "X" ]; then
    [[ "$debug" ]] && echo 2>&1 "DEBUG: Fix Cache timeout (systemd read)."
    if key_id=$(keyctl request user "$keyname" 2>/dev/null); then
      keyctl timeout "$key_id" $key_timeout 2>/dev/null
    fi
  fi
  exit $exitval
fi

# -----------------------------------------------------------------------------
# Shell Script version of user password with echo'd stars on TTY.
#
[[ "$debug" ]] && echo 2>&1 "DEBUG: Looped Key Stroke password read."

# Prompt is set above as part of command line options
#prompt="${*:-Password:}" # any other argument is the prompt to use

# The star or asterisk character to use in shell DIY version
star='#'  # Yes I know it is not a star!
          # But I wanted it to be different to systemd-ask-password
          # which could use '*' or bullet '•' depending on version.
if [ "$BASH_VERSINFO" -ge 3 ]; then
  # Unicode Bullets & Stars....   ⋅ • ●  ⋆ ✩ ✫ ✬ ✭ ✮ ✯ ✰ ★
  # Note: using $'\u...' requires BASH 4 syntax (RHEL 7 or Solaris 11)
  # but using direct UTF-8 characters works for BASH 3 (RHEL6 or Solaris 10)
  #star=$'\xe2\x80\xa2'  # UTF-8 encoded 'bullet'        '•'  or  $'\u2022'
  star=$'\xe2\x98\x85'   # UTF-8 encoded 'black star'    '★'  or  $'\u2605'
  #star=$'\xe2\x8b\x86'  # UTF-8 encoded 'star operator' '⋆'  or  $'\u22c6'
fi

# Grab the current TTY settings
# Save stdout (for password result) to descriptor 3
# Then force the IO to use the users TTY
#
# NOTE: Gnu and Linux "stty" uses stdin for the terminal to adjust
# BUT:  Solaris /usr/ucb/stty command uses stdout and NOT stdin!
# So we hedge or bets and set it up for either version.
#
exec 3>&1 </dev/tty >/dev/tty    # save stdout, talk to tty normally
stty_save=$(stty -g)             # save the terminal state
trap 'stty "$stty_save"' EXIT    # restore it on exit (just in case)
trap 'echo "===INTERUPT==="; exit 10' HUP INT QUIT ABRT TERM

# Turn off echo and control character handling...
# This is needed, in case the user types too fast for the "read" loop.
# And stops bash constantly switching terminal modes.
stty -icanon -echo

# Prompt and read password one character at a time
echo -n "$prompt "
while true; do
  IFS= read -r -N1 char
  # Note a Null character will return a empty string, as will EOF
  # Convert users key press to hexadecimal character code
  #code=$( echo -n "$char" | od -An -tx1 | tr -d ' \011' )
  code=$(printf '%02x' "'$char")

  # An alternative read method (not-builtin-bash)
  # code=$(head -c1 - | od -An -tx1 | tr -d ' \011')

  [[ "$debug" ]] && echo 2>&1 -n "'$code'"
  case "$code" in
  ''|0a|0d) break ;;               # Finish on EOF, Null, LineFeed, or Return
  03) error_exit=true; break ;;    # ^C Interrupt (the trap failed?)
  08|7f)                           # Backspace or Delete
      if [ -n "$PWORD" ]; then
        PWORD="$( echo "$PWORD" | sed 's/.$//' )"
        echo -n $'\b \b'           # not available on older shells
      fi
      ;;
  15)                              # ^U or kill line
      # Return back to start of line, prompt, and clear rest of the line
      printf '\r%s' "$prompt "; tput el
      PWORD=''
      ;;
  [01]?) ;;                        # Ignore ALL other control characters
  *)  PWORD="$PWORD$char"          # Record users keystroke
      #PWORD="$PWORD$(echo -e "\x$code")"  # alternative
      echo -n "$star"
      ;;
  esac
done

# Return TTY to normal, cancel exit trap
stty "$stty_save"
trap - EXIT HUP INT QUIT TERM

# Remove all the stars now that the input is finished (optional)
# I like to keep them for comparison when password is entered twice.
#printf '\r%s' "$prompt ===password entered==="; tput el
echo ""

# Output the resulting password to the original stdout
echo "$PWORD" >&3

# Cache the password into the linux kernel keyring (set timeout)...
if [ "X$keyname" != "X" ]; then
  [[ "$debug" ]] && echo 2>&1 "DEBUG: Cache password (looped key read)."
  if key_id=$(echo -n "$PWORD" |
                keyctl padd user "$keyname" @u 2>/dev/null); then
    keyctl timeout "$key_id" $key_timeout 2>/dev/null
  fi
fi
exit 0

