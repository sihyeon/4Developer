package com.project4D.fdpay.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Somin Lee (sayyo1120@gmail.com)
 * @version 2015-09-13.
 */
public class EventManager {
    private Map<String, List<UListener>> listeners = new HashMap<>();
    private static final List<UListener> EMPTY_LIST = Collections.unmodifiableList(new ArrayList<UListener>());
    private static EventManager instance = new EventManager();
    private EventManager() {}

    public static EventManager getInstance() {
        return instance;
    }

    public synchronized void registerListener(String category, UListener listener) {
        List<UListener> list = listeners.get(category);
        if(list==null)
            listeners.put(category, list = new CopyOnWriteArrayList<UListener>());
        list.add(listener);
    }

    public synchronized void unregisterListener(String category, UListener listener) {
        List<UListener> list = listeners.get(category);
        if(list==null)
            return;
        list.remove(listener);
    }

    public List<UListener> getListener(String category) {
        return listeners.get(category)==null ? EMPTY_LIST : listeners.get(category);
    }
}
