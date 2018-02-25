package cz.novros.reactiveworld;

import cz.novros.reactiveworld.observer.SingleObserver;
import io.reactivex.Single;

public class SingleExample {

	public static void main(final String[] args) {
		System.out.println("------------------------------------------");
		System.out.println("              Example of Single           ");
		System.out.println("------------------------------------------");
		create();
	}

	private static void create() {
		System.out.println("--- Single creation");
		final Single<Integer> single = Single.fromCallable(() -> 2);
		single.subscribe(new SingleObserver());
	}
}
