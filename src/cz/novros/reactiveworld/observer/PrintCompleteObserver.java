package cz.novros.reactiveworld.observer;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;

public class PrintCompleteObserver implements CompletableObserver {

	@Override
	public void onSubscribe(final Disposable disposable) {
		print("On subscribe.");
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
