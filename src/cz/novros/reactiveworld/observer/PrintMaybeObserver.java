package cz.novros.reactiveworld.observer;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;

public class PrintMaybeObserver implements MaybeObserver<Object> {

	@Override
	public void onSubscribe(final Disposable disposable) {
		print("On subscribe.");
	}

	@Override
	public void onSuccess(final Object o) {
		print("On success: " + o);
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
		System.out.println(text);
	}
}
