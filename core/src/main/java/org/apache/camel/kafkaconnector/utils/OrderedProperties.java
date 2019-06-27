package org.apache.camel.kafkaconnector.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

//XXX: can be removed once upgrading to camel 3.0.0-M4 or newer
public final class OrderedProperties extends Properties {

    private final Map<String, String> map = new LinkedHashMap<>();

    public OrderedProperties() {
    }

    @Override
    public synchronized Object put(Object key, Object value) {
        return map.put(key.toString(), value.toString());
    }

    @Override
    public synchronized void putAll(Map<?, ?> t) {
        for (Map.Entry<?, ?> entry : t.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public synchronized Object get(Object key) {
        return map.get(key);
    }

    @Override
    public synchronized boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public synchronized Object remove(Object key) {
        return map.remove(key);
    }

    @Override
    public synchronized void clear() {
        map.clear();
    }

    @Override
    public String getProperty(String key) {
        return map.get(key);
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public synchronized Enumeration<Object> keys() {
        return new Vector<Object>(map.keySet()).elements();
    }

    @Override
    public Set<Object> keySet() {
        return new LinkedHashSet<>(map.keySet());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Map.Entry<Object, Object>> entrySet() {
        Set entrySet = map.entrySet();
        return (Set<Map.Entry<Object, Object>>) entrySet;
    }

    @Override
    public synchronized int size() {
        return map.size();
    }

    @Override
    public Set<String> stringPropertyNames() {
        return map.keySet();
    }

    @Override
    public Collection<Object> values() {
        return new ArrayList<>(map.values());
    }

    @Override
    public synchronized String toString() {
        return map.toString();
    }
}
