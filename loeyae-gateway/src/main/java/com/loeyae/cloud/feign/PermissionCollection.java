package com.loeyae.cloud.feign;

import lombok.Data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * .
 *
 * @date: 2020-08-11
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@Data
public class PermissionCollection {

    private Set<Permission> permissions = new HashSet<>();
    
    public int size() {
        return permissions.size();
    }

    
    public boolean isEmpty() {
        return permissions.isEmpty();
    }

    
    public boolean contains(Object o) {
        return permissions.contains(o);
    }

    
    public Iterator<Permission> iterator() {
        return permissions.iterator();
    }

    
    public Object[] toArray() {
        return permissions.toArray();
    }

    
    public <T> T[] toArray(T[] ts) {
        return permissions.toArray(ts);
    }

    
    public boolean add(Permission permission) {
        return permissions.add(permission);
    }

    
    public boolean remove(Object o) {
        return permissions.remove(o);
    }

    
    public boolean containsAll(Collection<?> collection) {
        return permissions.containsAll(collection);
    }

    
    public boolean addAll(Collection<? extends Permission> collection) {
        return permissions.addAll(collection);
    }

    
    public boolean removeAll(Collection<?> collection) {
        return permissions.removeAll(collection);
    }

    
    public boolean retainAll(Collection<?> collection) {
        return permissions.retainAll(collection);
    }

    
    public void clear() {
        permissions.clear();
    }


    /**
     * searchCode
     *
     * @param code
     * @return
     */
    public Permission searchCode(String code)
    {
        Set<Permission> searched = permissions.stream()
                .filter(item -> item.getCode().equals(code))
                .collect(Collectors.toSet());
        return searched.iterator().next();
    }

}