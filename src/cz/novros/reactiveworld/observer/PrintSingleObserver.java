package cz.novros.reactiveworld.observer;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class PrintSingleObserver implements SingleObserver<Object> {

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

	private void print(final String text) {
		System.out.println(text);
	}
}
