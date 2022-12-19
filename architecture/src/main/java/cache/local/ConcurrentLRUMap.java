package cache.local;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @class ConcurrentLRUMap
 * @desc
 * @since 2020-10-20
 */
public class ConcurrentLRUMap<K, V> implements Serializable {
    private static final long serialVersionUID = -7309303699204841905L;
    private static final int DEFAULT_INITIAL_CAPACITY = 1024;
    private static final int DEFAULT_MAX_SEGMENTS = 16;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    private final int segmentMask;
    private final int segmentShift;
    private ConcurrentLRUMap.SegmentHashMap<K, V>[] segments;

    public ConcurrentLRUMap() {
        this(1024);
    }

    public ConcurrentLRUMap(int maxSize) {
        if (maxSize < 0) {
            throw new IllegalArgumentException("maxSize must > 0");
        } else {
            if (0 != maxSize % 16) {
                maxSize = (maxSize / 16 + 1) * 16;
            }

            int sshift = 0;

            int ssize;
            for (ssize = 1; ssize < 16; ssize <<= 1) {
                ++sshift;
            }

            if (ssize != 16) {
                throw new IllegalArgumentException("size must be power-of-two!");
            } else {
                this.segmentShift = 32 - sshift;
                this.segmentMask = ssize - 1;
                this.segments = new ConcurrentLRUMap.SegmentHashMap[ssize];
                if (maxSize > 1073741824) {
                    maxSize = 1073741824;
                }

                int c = maxSize / ssize;
                if (c * ssize != maxSize) {
                    throw new IllegalArgumentException("make sure: maxSize / 16 == 0");
                } else {
                    if (c * ssize < maxSize) {
                        ++c;
                    }

                    int cap;
                    for (cap = 1; cap < c; cap <<= 1) {
                    }

                    for (int i = 0; i < this.segments.length; ++i) {
                        this.segments[i] = new ConcurrentLRUMap.SegmentHashMap(cap);
                    }

                }
            }
        }
    }

    public V get(K key) {
        int hash = hash(key.hashCode());
        return this.segmentFor(hash).getEntry(key);
    }

    public Object put(K key, V value) {
        if (null == value) {
            throw new NullPointerException("value could not be null!");
        } else {
            int hash = hash(key.hashCode());
            return this.segmentFor(hash).addEntry(key, value);
        }
    }

    public Object remove(K key) {
        int hash = hash(key.hashCode());
        return this.segmentFor(hash).remove(key);
    }

    public boolean containsKey(K key) {
        int hash = hash(key.hashCode());
        return this.segmentFor(hash).containsKey(key);
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public Set<K> keySet() {
        Set<K> rtn = new HashSet();

        for (int i = 0; i < this.segments.length; ++i) {
            Set<K> set = this.segments[i].keySet();
            rtn.addAll(set);
        }

        return rtn;
    }

    public synchronized void clear() {
        for (int i = 0; i < this.segments.length; ++i) {
            this.segments[i].clear();
        }

    }

    public int size() {
        int sum = 0;

        for (int i = 0; i < this.segments.length; ++i) {
            sum += this.segments[i].size();
        }

        return sum;
    }

    private static final int hash(int h) {
        h += h << 15 ^ -12931;
        h ^= h >>> 10;
        h += h << 3;
        h ^= h >>> 6;
        h += (h << 2) + (h << 14);
        return h ^ h >>> 16;
    }

    private final ConcurrentLRUMap.SegmentHashMap<K, V> segmentFor(int hash) {
        return this.segments[hash >>> this.segmentShift & this.segmentMask];
    }

    private static final class SegmentHashMap<KK, VV> extends LinkedHashMap<KK, VV> {
        private static final long serialVersionUID = 6488943653970934521L;
        private final Lock lock = new ReentrantLock();
        private int maxSize;

        public SegmentHashMap(int maxSize) {
            super(maxSize);
            this.maxSize = maxSize;
        }

        public VV addEntry(KK key, VV value) {
            this.lock.lock();

            VV object;
            try {
                VV oldvalue = super.put(key, value);
                object = oldvalue;
            } finally {
                this.lock.unlock();
            }

            return object;
        }

        public VV getEntry(KK key) {
            this.lock.lock();

            VV obj;
            try {
                VV value = this.get(key);
                if (null != value) {
                    obj = value;
                    return obj;
                }

                super.remove(key);
                obj = null;
            } finally {
                this.lock.unlock();
            }

            return obj;
        }

        public VV remove(Object key) {
            this.lock.lock();

            VV obj;
            try {
                obj = super.remove(key);
            } finally {
                this.lock.unlock();
            }

            return obj;
        }

        public void clear() {
            this.lock.lock();

            try {
                super.clear();
            } finally {
                this.lock.unlock();
            }

        }

        public boolean removeEldestEntry(Map.Entry<KK, VV> eldest) {
            return this.size() > this.maxSize;
        }
    }
}
