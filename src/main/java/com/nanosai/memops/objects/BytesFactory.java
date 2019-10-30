package com.nanosai.memops.objects;

import com.nanosai.memops.bytes.BytesAllocatorAutoDefrag;

public class BytesFactory implements IObjectFactory<Bytes> {

    private BytesAllocatorAutoDefrag bytesAllocatorAutoDefrag = null;

    public BytesFactory(BytesAllocatorAutoDefrag allocator){
        this.bytesAllocatorAutoDefrag = allocator;
    }

    @Override
    public Bytes instance(ObjectPool<Bytes> objectPool) {
        Bytes block = new Bytes();
        block.init(this.bytesAllocatorAutoDefrag, objectPool);
        return block;
    }
}
