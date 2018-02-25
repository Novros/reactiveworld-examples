package cz.novros.reactiveworld;

import cz.novros.reactiveworld.observer.PrintObserver;
import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;

public class SubjectExample {

	public static void main(final String[] args) {
		System.out.println("------------------------------------------");
		System.out.println("         Examples of Subject types        ");
		System.out.println("------------------------------------------");

		async();
		asyncError();

		behaviour();
		behaviourError();

		publish();
		publishError();

		replay();
		replayError();
	}

	private static void async() {
		System.out.println("--- Async subject");
		final Subject<Integer> subject = AsyncSubject.create();
		subject.subscribe(new PrintObserver());

		System.out.println("- calling on next");
		subject.onNext(1);
		subject.onNext(2);

		System.out.println("- subscribing second observer");
		subject.subscribe(new PrintObserver());

		System.out.println("- subscribing subject to observable");
		final Observable<Integer> observable = Observable.fromArray(8, 9);
		observable.subscribe(subject);
	}

	private static void asyncError() {
		System.out.println("--- Async subject error");
		final Subject<Integer> subject = AsyncSubject.create();
		subject.subscribe(new PrintObserver());

		System.out.println("- calling on error");
		subject.onError(new IllegalArgumentException());

		System.out.println("- calling on next");
		subject.onNext(1);
	}

	private static void behaviour() {
		System.out.println("--- Behaviour subject");
		final Subject<Integer> subject = BehaviorSubject.createDefault(0);
		subject.subscribe(new PrintObserver());

		System.out.println("- calling on next");
		subject.onNext(1);
		subject.onNext(2);

		System.out.println("- subscribing second observer");
		subject.subscribe(new PrintObserver());

		System.out.println("- subscribing subject to observable");
		final Observable<Integer> observable = Observable.fromArray(8, 9);
		observable.subscribe(subject);
	}

	private static void behaviourError() {
		System.out.println("--- Behaviour subject error");
		final Subject<Integer> subject = BehaviorSubject.create();
		subject.subscribe(new PrintObserver());

		System.out.println("- calling on error");
		subject.onError(new IllegalArgumentException());

		System.out.println("- calling on next");
		subject.onNext(1);
	}

	private static void publish() {
		System.out.println("--- Publish subject");
		final Subject<Integer> subject = PublishSubject.create();
		subject.subscribe(new PrintObserver());

		System.out.println("- calling on next");
		subject.onNext(1);
		subject.onNext(2);

		System.out.println("- subscribing second observer");
		subject.subscribe(new PrintObserver());

		System.out.println("- subscribing subject to observable");
		final Observable<Integer> observable = Observable.fromArray(8, 9);
		observable.subscribe(subject);
	}

	private static void publishError() {
		System.out.println("--- Publish subject error");
		final Subject<Integer> subject = PublishSubject.create();
		subject.subscribe(new PrintObserver());

		System.out.println("- calling on error");
		subject.onError(new IllegalArgumentException());

		System.out.println("- calling on next");
		subject.onNext(1);
	}

	private static void replay() {
		System.out.println("--- Replay subject");
		final Subject<Integer> subject = ReplaySubject.create();

		System.out.println("- calling on next");
		subject.onNext(1);

		System.out.println("- subscribing observer");
		subject.subscribe(new PrintObserver());

		System.out.println("- calling on next");
		subject.onNext(1);
		subject.onNext(2);

		System.out.println("- subscribing second observer");
		subject.subscribe(new PrintObserver());

		System.out.println("- subscribing subject to observable");
		final Observable<Integer> observable = Observable.fromArray(8, 9);
		observable.subscribe(subject);
	}

	private static void replayError() {
		System.out.println("--- Replay subject error");
		final Subject<Integer> subject = ReplaySubject.create();
		subject.subscribe(new PrintObserver());

		System.out.println("- calling on error");
		subject.onError(new IllegalArgumentException());

		System.out.println("- calling on next");
		subject.onNext(1);
	}
}
