package cz.novros.reactiveworld.observable;

import java.util.concurrent.TimeUnit;

import cz.novros.reactiveworld.observer.PrintObserver;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class ObservableCombining {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("------------------------------------------");
		System.out.println("    Example of combining of Observable    ");
		System.out.println("------------------------------------------");

		join();
		merge();
		startWith();
		concat();
		_switch();
		zip();
		combineLatest();
	}

	private static void join() throws InterruptedException {
		System.out.println("--- Join");
		final Subject<String> observableA = PublishSubject.create();
		final Subject<String> observableB = PublishSubject.create();

		// Join emitted items from overlapping windows of two observables
		// Source observable opens the window
		// 1st joining Observable
		// 2st argument tells closing of the source observable window (no matter on value, it just needs onNext or onComplete called)
		// 3st argument tells closing of the joining observable window (no matter on value, it just needs onNext or onComplete called)
		// 4st argument is function for combining values from first observable and second observable
		observableA.join(observableB,
				source -> Observable.timer(20, TimeUnit.MILLISECONDS), // Window open for 20 millis
				joinWith -> Observable.timer(100, TimeUnit.MILLISECONDS), // Window open for 100 millis
				(source, joinWith) -> source + " - " + joinWith)
				.subscribe(new PrintObserver());

		observableA.onNext("A1");
		observableA.onNext("A2");
		observableB.onNext("B1");
		Thread.sleep(30);
		observableA.onNext("A3");
		Thread.sleep(30);
		observableB.onNext("B2");
		Thread.sleep(30);
		observableB.onNext("B3");
		observableA.onNext("A4");
		Thread.sleep(30);
		observableB.onNext("B4");
	}

	private static void merge() {
		// Method variants: merge, mergeDelayError, mergeArray, mergeArrayDelayError
		System.out.println("--- Merge");
		final Subject<String> observableA = PublishSubject.create();
		final Subject<String> observableB = PublishSubject.create();

		System.out.println("- without error");
		observableA.mergeWith(observableB).subscribe(new PrintObserver());
		observableA.onNext("A1");
		observableA.onNext("A2");
		observableB.onNext("B1");
		observableA.onNext("A3");
		observableB.onNext("B2");
		observableB.onNext("B3");
		observableA.onNext("A4");
		observableB.onNext("B4");

		System.out.println("- with error");
		observableA.mergeWith(observableB).subscribe(new PrintObserver());
		observableA.onNext("A1");
		observableA.onNext("A2");
		observableB.onNext("B1");
		observableA.onError(new IllegalArgumentException());
		observableB.onNext("B2");
		observableB.onNext("B3");
		observableA.onNext("A4");
		observableB.onNext("B4");
	}

	private static void startWith() {
		// Method variants: startWith, startWithArray
		System.out.println("--- StartWith");
		final Observable<Integer> observableA = Observable.range(0, 4);
		final Observable<Integer> observableB = Observable.range(10, 4);

		System.out.println("- Start with constant");
		observableA.startWith(110).subscribe(new PrintObserver());

		System.out.println("- Start with observable");
		observableA.startWith(observableB).subscribe(new PrintObserver());

		System.out.println("- Start with array");
		observableA.startWithArray(110, 111, 112).subscribe(new PrintObserver());
	}

	private static void concat() {
		// Method variants: concat, concatDelayError, concatEager, concatArray, contactArrayDelayError, concatArrayEager
		System.out.println("--- Concat");
		final Observable<Integer> observableA = Observable.range(0, 4);
		final Observable<Integer> observableB = Observable.range(10, 4);

		observableA.concatWith(observableB).subscribe(new PrintObserver());
	}

	private static void _switch() {
		// Method variants: switchIfEmpty, switchOnNext, switchOnNextDelayError
		System.out.println("--- Switch");
		final Observable<Integer> observableA = Observable.range(0, 4);
		final Observable<Integer> observableB = Observable.range(10, 4);

		System.out.println("- Switch if empty");
		observableA.switchIfEmpty(observableB).subscribe(new PrintObserver());
		Observable.empty().switchIfEmpty(observableB).subscribe(new PrintObserver());

		System.out.println("- Switch");
		Observable.switchOnNext(Observable.just(observableA, observableB)).subscribe(new PrintObserver());
	}

	private static void zip() {
		// Method variants: zip, zipArray, zipIterable
		System.out.println("--- Zip");
		final Observable<Integer> observableA = Observable.range(0, 4);
		final Observable<Integer> observableB = Observable.range(10, 4);
		final BiFunction<Integer, Integer, String> zipper = (integer, integer2) -> "A" + integer + "B" + integer2;

		observableA.zipWith(observableB, zipper).subscribe(new PrintObserver("Zip"));

		System.out.println("- delayError - false");
		// Default is false for delayError
		final Subject<Integer> errorObservable = PublishSubject.create();
		errorObservable.zipWith(observableB, zipper, false).subscribe(new PrintObserver("delayError=false"));
		errorObservable.onNext(2);
		errorObservable.onError(new IllegalArgumentException());
		errorObservable.onNext(3);
		errorObservable.onNext(4);

		System.out.println("- delayError - true");
		final Subject<Integer> errorObservable2 = PublishSubject.create();
		errorObservable2.zipWith(observableB, zipper).subscribe(new PrintObserver());
		errorObservable2.zipWith(observableB, zipper, true).subscribe(new PrintObserver("delayError=true"));
		errorObservable2.onNext(2);
		errorObservable2.onError(new IllegalArgumentException());
		errorObservable2.onNext(3);
		errorObservable2.onNext(4);
	}

	private static void combineLatest() {
		// Method variants: combineLatest, combineLatestDelayError
		System.out.println("--- CombineLast");
		final Subject<String> observableA = PublishSubject.create();
		final Subject<String> observableB = PublishSubject.create();

		Observable.combineLatest(observableA, observableB, (integer, integer2) -> integer + integer2).subscribe(new PrintObserver());

		observableA.onNext("A1");
		observableA.onNext("A2");
		observableB.onNext("B1");
		observableA.onNext("A3");
		observableB.onNext("B2");
		observableB.onNext("B3");
		observableA.onNext("A4");
		observableB.onNext("B4");
	}
}
