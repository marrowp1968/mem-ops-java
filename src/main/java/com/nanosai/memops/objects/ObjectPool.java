package com.nanosai.memops.objects;

import java.util.Stack;

public class ObjectPool<T> implements IObjectPool<T> {

    private int               instances     = 0;
    private int               capacity      = 0;
    private IObjectFactory<T> objectFactory = null;
    private Stack<T>          stack         = new Stack<T>();

    public ObjectPool(int capacity) {
        this.capacity = capacity;
    }

    public ObjectPool(int capacity, IObjectFactory<T> objectFactory){
        this.capacity      = capacity;
        this.objectFactory = objectFactory;
    }

    public int instances() {
        return this.instances;
    }

    public int instancesFree() {
        return this.stack.size();
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    public void setObjectFactory(IObjectFactory<T> objectFactory){
        this.objectFactory = objectFactory;
    }

    @Override
    public T instance() {
        if(this.stack.size() > 0) {
            return this.stack.pop();
        }
        if(this.instances == this.capacity) {
            return null;
        }
        this.instances++;
        return this.objectFactory.instance(this);
    }

    public void free(T instance){
        this.stack.push(instance);
    }


}
