package com.loeyae.cloud.feign;

import lombok.Data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * MenuCollection
 *
 * @date: 2020-08-11
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@Data
public class MenuCollection {

    private Set<Menu> menus = new HashSet<>();

    public int size() {
        return menus.size();
    }

    
    public boolean isEmpty() {
        return menus.isEmpty();
    }

    
    public boolean contains(Object o) {
        return menus.contains(o);
    }

    
    public Iterator<Menu> iterator() {
        return menus.iterator();
    }

    
    public Object[] toArray() {
        return menus.toArray();
    }

    
    public <T> T[] toArray(T[] ts) {
        return menus.toArray(ts);
    }

    
    public boolean add(Menu menu) {
        return menus.add(menu);
    }

    
    public boolean remove(Object o) {
        return menus.remove(o);
    }

    
    public boolean containsAll(Collection<?> collection) {
        return menus.containsAll(collection);
    }

    
    public boolean addAll(Collection<? extends Menu> collection) {
        return menus.addAll(collection);
    }

    
    public boolean removeAll(Collection<?> collection) {
        return getMenus().removeAll(collection);
    }

    
    public boolean retainAll(Collection<?> collection) {
        return menus.retainAll(collection);
    }

    
    public void clear() {
        menus.clear();
    }

    /**
     * searchUrl
     *
     * @param url
     * @return
     */
    public Menu searchUrl(String url)
    {
        Set<Menu> searched = menus.stream()
                .filter(item -> item.getUrl().equals(url))
                .collect(Collectors.toSet());
        return searched.iterator().next();
    }

}