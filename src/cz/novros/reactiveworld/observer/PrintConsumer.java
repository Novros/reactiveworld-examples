package cz.novros.reactiveworld.observer;

import io.reactivex.functions.Consumer;

public class PrintConsumer implements Consumer<Object> {

	@Override
	public void accept(final Object o) throws Exception {
		System.out.println("Accept: " + o);
	}
}
