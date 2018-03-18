package cz.novros.reactiveworld.observer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PrintObserver implements Observer<Object> {

	private final String name;

	public PrintObserver() {
		this.name = null;
	}

	public PrintObserver(final String name) {
		this.name = name;
	}

	@Override
	public void onSubscribe(final Disposable disposable) {
		print("On subscribe.");
	}

	@Override
	public void onNext(final Object o) {
		print("On next: " + o);
	}

	@Override
	public void onError(final Throwable throwable) {
		print("On error: " + throwable.getClass().getSimpleName() + ": " + throwable.getMessage());
	}

	@Override
	public void onComplete() {
		print("On completed.");
	}

	private void print(final String text) {
		System.out.println((name == null ? "" : "[" + name + "] ") + text);
	}
}
