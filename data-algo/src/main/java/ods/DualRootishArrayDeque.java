package ods;

import java.util.List;

/**
 * This is d DualArrayDeque, but with the internal Lists are implemented as
 * RootishArrayStacks.  This class provides an implementation of a Deque
 * with a space overhead of only O(sqrt(n)).
 *
 * @param <T>
 * @author morin
 */
public class DualRootishArrayDeque<T> extends DualArrayDeque<T> {
	public DualRootishArrayDeque(Class<T> t) {
		super(t);
	}

    protected List<T> newStack() {
		return new RootishArrayStack<T>(f.type());
	}
}
