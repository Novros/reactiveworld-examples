package cz.novros.reactiveworld.observable;

import java.util.concurrent.TimeUnit;

import cz.novros.reactiveworld.observer.PrintCompleteObserver;
import cz.novros.reactiveworld.observer.PrintMaybeObserver;
import cz.novros.reactiveworld.observer.PrintObserver;
import cz.novros.reactiveworld.observer.PrintSingleObserver;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class ObservableFiltering {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("------------------------------------------");
		System.out.println("      Example of filtering Observable     ");
		System.out.println("------------------------------------------");
		first();
		last();
		elementAt();
		take();
		takeLast();
		debounce();
		debounceOneValue();
		distinct();
		filter();
		ignoreElements();
		sample();
		skip();
		skipLast();
	}

	private static void first() {
		System.out.println("--- First");
		Observable<Integer> observable = Observable.range(1, 10);
		observable.first(999).subscribe(new PrintSingleObserver());
		observable.firstElement().subscribe(new PrintMaybeObserver());
		observable.firstOrError().subscribe(new PrintSingleObserver());

		System.out.println("- empty observable");
		observable = Observable.empty();
		observable.first(999).subscribe(new PrintSingleObserver());
		observable.firstElement().subscribe(new PrintMaybeObserver());
		observable.firstOrError().subscribe(new PrintSingleObserver());
	}

	private static void last() {
		System.out.println("--- Last");
		Observable<Integer> observable = Observable.range(1, 10);
		observable.last(999).subscribe(new PrintSingleObserver());
		observable.lastElement().subscribe(new PrintMaybeObserver());
		observable.lastOrError().subscribe(new PrintSingleObserver());

		System.out.println("- empty observable");
		observable = Observable.empty();
		observable.last(999).subscribe(new PrintSingleObserver());
		observable.lastElement().subscribe(new PrintMaybeObserver());
		observable.lastOrError().subscribe(new PrintSingleObserver());
	}

	private static void elementAt() {
		System.out.println("--- ElementAt");
		Observable<Integer> observable = Observable.range(1, 10);
		observable.elementAt(2).subscribe(new PrintMaybeObserver());
		observable.elementAt(2, 999).subscribe(new PrintSingleObserver());
		observable.elementAtOrError(11).subscribe(new PrintSingleObserver());

		System.out.println("- empty observable");
		observable = Observable.empty();
		observable.elementAt(2).subscribe(new PrintMaybeObserver());
		observable.elementAt(2, 999).subscribe(new PrintSingleObserver());
		observable.elementAtOrError(11).subscribe(new PrintSingleObserver());
	}

	private static void take() {
		System.out.println("--- Take");
		Observable<Integer> observable = Observable.range(1, 10);
		observable.take(2).subscribe(new PrintObserver());

		System.out.println("- empty observable");
		observable = Observable.empty();
		observable.take(2).subscribe(new PrintObserver());

		System.out.println("- error observable");
		observable = Observable.error(new IllegalAccessException());
		observable.take(2).subscribe(new PrintObserver());
	}

	private static void takeLast() {
		System.out.println("--- Take last");
		Observable<Integer> observable = Observable.range(1, 10);
		observable.takeLast(2).subscribe(new PrintObserver());

		System.out.println("- empty observable");
		observable = Observable.empty();
		observable.takeLast(2).subscribe(new PrintObserver());

		System.out.println("- error observable");
		observable = Observable.error(new IllegalAccessException());
		observable.takeLast(2).subscribe(new PrintObserver());
	}

	private static void debounce() throws InterruptedException {
		System.out.println("--- Debounce");
		Subject<Integer> observable = PublishSubject.create();
		observable.onNext(1);
		observable.onNext(2);
		observable.debounce(100, TimeUnit.MILLISECONDS).subscribe(new PrintObserver());
		observable.onNext(3);
		observable.onNext(4);
		Thread.sleep(110);
		observable.onNext(5);
		Thread.sleep(110);
		observable.onNext(6);
		observable.onNext(7);
		observable.onComplete();
	}

	private static void debounceOneValue() throws InterruptedException {
		System.out.println("--- Debounce one value");
		Subject<Integer> observable = PublishSubject.create();
		observable.onNext(1);
		observable.onNext(2);
		observable.debounce(100, TimeUnit.MILLISECONDS).subscribe(new PrintObserver());
		observable.onNext(3);
		Thread.sleep(90);
		observable.onNext(4);
		observable.onNext(5);
		Thread.sleep(90);
		observable.onNext(6);
		observable.onNext(7);
		observable.onComplete();
	}

	private static void distinct() {
		System.out.println("--- Distinct");
		Observable<Integer> observable = Observable.just(1, 2, 3, 1, 4, 2, 5, 6);
		observable.distinct().subscribe(new PrintObserver());
		observable.distinct(integer -> integer % 3).subscribe(new PrintObserver());
	}

	private static void filter() {
		System.out.println("--- Filter");
		Observable<Integer> observable = Observable.range(1, 10);
		observable.filter(integer -> integer % 2 == 0).subscribe(new PrintObserver());
	}

	private static void ignoreElements() {
		System.out.println("--- Ignore elements");
		Observable<Integer> observable = Observable.range(1, 10);
		observable.ignoreElements().subscribe(new PrintCompleteObserver());
	}

	private static void sample() throws InterruptedException {
		System.out.println("--- Sample");
		Subject<Integer> observable = PublishSubject.create();
		observable.onNext(1);
		observable.onNext(2);
		observable.sample(100, TimeUnit.MILLISECONDS).subscribe(new PrintObserver());
		observable.onNext(3);
		observable.onNext(4);
		Thread.sleep(110);
		observable.onNext(5);
		Thread.sleep(110);
		observable.onNext(6);
		observable.onNext(7);
		observable.onComplete();
	}

	private static void skip() {
		System.out.println("--- Skip");
		Observable<Integer> observable = Observable.range(1, 10);
		observable.skip(3).subscribe(new PrintObserver());
	}

	private static void skipLast() {
		System.out.println("--- Skip last");
		Observable<Integer> observable = Observable.range(1, 10);
		observable.skipLast(3).subscribe(new PrintObserver());
	}
}
