package cz.novros.reactiveworld.observer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PrintObserver implements Observer<Object> {

	@Override
	public void onSubscribe(final Disposable disposable) {
		System.out.println("On subscribe.");
	}

	@Override
	public void onNext(final Object o) {
		System.out.println("On next: " + o);
	}

	@Override
	public void onError(final Throwable throwable) {
		System.out.println("On error: " + throwable.getClass().getSimpleName() + ": " + throwable.getMessage());
	}

	@Override
	public void onComplete() {
		System.out.println("On completed.");
	}
}
