package cz.novros.reactiveworld.observable;

import cz.novros.reactiveworld.observer.PrintObserver;
import io.reactivex.Observable;

public class ObservableFunctions {

	public static void main(final String[] args) {
		System.out.println("------------------------------------------");
		System.out.println("    Example of functions of Observable    ");
		System.out.println("------------------------------------------");
		merge();
	}

	private static void merge() {
		System.out.println("--- Merge function");
		final Observable<Integer> observableA = Observable.just(1, 2);
		final Observable<Integer> observableB = Observable.just(5, 6);
		observableA.mergeWith(observableB).subscribe(new PrintObserver());
	}
}
