package cz.novros.reactiveworld;

import cz.novros.reactiveworld.observer.PrintConsumer;
import cz.novros.reactiveworld.observer.PrintObserver;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class ObservableCreation {

	public static void main(final String[] args) throws InterruptedException {
		System.out.println("------------------------------------------");
		System.out.println("    Example of creation of Observable     ");
		System.out.println("------------------------------------------");

		unsubscribing();

		create();
		just();
		just2();
		fromArray();
		fromIterable();
		fromCallable();

		range();
		empty();
		never();
		throwO();

		interval();
		timer();
		repeat();
	}

	private static void create() {
		System.out.println("--- Create creation");
		final Observable<Integer> observable = Observable.create(observableEmitter -> {
            for (int i = 0; i < 3; i++) {
                observableEmitter.onNext(i);
            }
        });
		observable.subscribe(new PrintObserver());
	}

	private static void just() {
		System.out.println("--- Just creation");
		final Observable<Integer> observable = Observable.just(5);
		observable.subscribe(new PrintObserver());
	}

	private static void just2() {
		System.out.println("--- Just creation");
		final Observable<Integer> observable = Observable.just(10, 3, 5);
		observable.subscribe(new PrintObserver());
	}

	private static void fromArray() {
		System.out.println("--- From array creation");
		final Observable<Integer> observable = Observable.fromArray(1, 2, 3);
		observable.subscribe(new PrintObserver());
	}

	private static void fromCallable() {
		System.out.println("--- From callable creation");
		final Observable<Integer> observable = Observable.fromCallable(() -> 6);
		observable.subscribe(new PrintObserver());
	}

	private static void fromIterable() {
		System.out.println("--- From iterable creation");
		final Collection<Integer> iterable = new ArrayList<>();
		iterable.add(2); iterable.add(9); iterable.add(4);
		final Observable<Integer> observable = Observable.fromIterable(iterable);
		observable.subscribe(new PrintObserver());
	}

	private static void unsubscribing() {
		System.out.println("--- Unsubscribe from observable");
		final Observable<Integer> observable = Observable.just(10, 5);
		final Disposable disposable = observable.subscribe(new PrintConsumer());
		disposable.dispose();
	}

	private static void range() {
		System.out.println("--- Range observable");
		final Observable<Integer> observable = Observable.range(2, 5);
		observable.subscribe(new PrintObserver());
	}

	private static void empty() {
		System.out.println("--- Empty observable");
		final Observable<Integer> observable = Observable.empty();
		observable.subscribe(new PrintObserver());
	}

	private static void never() {
		System.out.println("--- Never observable");
		final Observable<Integer> observable = Observable.never();
		observable.subscribe(new PrintObserver());
	}

	private static void throwO() {
		System.out.println("--- Throw observable");
		final Observable<Integer> observable = Observable.error(new IllegalArgumentException());
		observable.subscribe(new PrintObserver());
	}

	private static void interval() throws InterruptedException {
		System.out.println("--- Interval observable");
		final Observable<Long> observable = Observable.interval(0, 1, TimeUnit.SECONDS);
		observable.subscribe(new PrintObserver());
		Thread.sleep(3000);
	}

	private static void repeat() {
		System.out.println("--- Repeat observable");
		// FIXME Implement
		//final Observable<Long> observable = Observable.(2, 1, TimeUnit.SECONDS);
		//observable.subscribe(new PrintConsumer());
	}

	private static void timer() throws InterruptedException {
		System.out.println("--- Timer observable");
		final Observable<Long> observable = Observable.timer(2, TimeUnit.SECONDS);
		observable.subscribe(new PrintObserver());
		Thread.sleep(3000);

	}
}
