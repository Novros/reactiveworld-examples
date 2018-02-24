package cz.novros.reactiveworld;

import cz.novros.reactiveworld.observer.PrintConsumer;
import cz.novros.reactiveworld.observer.PrintObserver;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class ObservableCreation {

	public static void main(final String[] args) {
		System.out.println("------------------------------------------");
		System.out.println("    Example of creation of Observable     ");
		System.out.println("------------------------------------------");
		just();
		fromArray();
		unsubscribing();
	}

	private static void just() {
		System.out.println("--- Just creation");
		final Observable<Integer> observable = Observable.just(10, 5);
		observable.subscribe(new PrintObserver());
	}

	private static void fromArray() {
		System.out.println("--- From array creation");
		final Observable<Integer> observable = Observable.fromArray(1, 2, 3);
		observable.subscribe(new PrintObserver());
	}

	private static void unsubscribing() {
		System.out.println("--- Unsubscribe from observable");
		final Observable<Integer> observable = Observable.just(10, 5);
		final Disposable disposable = observable.subscribe(new PrintConsumer());
		disposable.dispose();
	}
}
