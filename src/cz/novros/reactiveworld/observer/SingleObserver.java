package cz.novros.reactiveworld.observer;

import io.reactivex.disposables.Disposable;

public class SingleObserver implements io.reactivex.SingleObserver<Object> {

	@Override
	public void onSubscribe(final Disposable disposable) {
		System.out.println("On subscribe.");
	}

	@Override
	public void onSuccess(final Object o) {
		System.out.println("On success: " + o);
	}

	@Override
	public void onError(final Throwable throwable) {
		System.out.println("On error: " + throwable.getClass().getSimpleName() + ": " + throwable.getMessage());
	}
}
