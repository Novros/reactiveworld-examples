package cz.novros.reactiveworld.observable;

import java.util.concurrent.TimeUnit;

import cz.novros.reactiveworld.observer.PrintConsumer;
import cz.novros.reactiveworld.observer.PrintObserver;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;

public class ObservableErrorAndUtilityFunctions {

	public static void main(String[] args) throws InterruptedException {
		_catch();
		retry();
		delay();
		_do();
		materialize();
		serialize();
		subscribe();
		observerOn();
		subscribeOn();
		timeInterval();
		timeout();
		timestamp();
		using();
	}

	private static void _catch() {
		System.out.println("--- catch");
		// Method variants: onErrorResumeNext(Function), onErrorResume(ObservableSource), onErrorReturn(Function), onErrorReturnItem(T)
		System.out.println("- Return item");
		Subject<Integer> observable = PublishSubject.create();
		observable.onErrorReturnItem(999).subscribe(new PrintObserver());
		observable.onNext(1);
		observable.onError(new IllegalArgumentException());
		observable.onNext(2);

		System.out.println("- Resume next");
		observable = PublishSubject.create();
		observable.onNext(1);
		observable.onError(new IllegalArgumentException());
		observable.onNext(2);
		observable.onErrorResumeNext(Observable.just(997, 998)).subscribe(new PrintObserver());
	}

	private static void retry() {
		System.out.println("--- retry");
		// Method variants: retry, retry(times), retry(throwable_predicate), retry(BiPredicate<Integer, Throwable>), retryUntil(BooleanSupplier)
		final Subject<Integer> subject = ReplaySubject.create();
		subject.onNext(1);
		subject.onNext(2);
		subject.onError(new IllegalArgumentException());
		subject.onNext(3);
		subject.onError(new IllegalStateException());
		subject.onNext(4);
		subject.onError(new IllegalArgumentException());
		subject.onNext(5);
		subject.onComplete();

		subject.retry(2).subscribe(new PrintObserver("2 times"));
		subject.retry(throwable -> throwable instanceof IllegalStateException).subscribe(new PrintObserver("ISE"));
	}

	private static void delay() throws InterruptedException {
		System.out.println("--- delay");
		// Method variants: delay(delay, timeUnit), delay(delay, timeUnit, delayError), delay(delay, timeUnit, scheduler)
		final Observable<Integer> observable = Observable.range(0, 4);
		observable.delay(20, TimeUnit.MILLISECONDS).subscribe(new PrintObserver());
		Thread.sleep(19);
		System.out.println("After 19 millis");
		Thread.sleep(1);
		System.out.println("After 20 millis");
	}

	private static void _do() {
		System.out.println("--- do");
		// Method variants: doAfterNext, doAfterTerminate, doFinally, doOnComplete, doOnDispose, doOnEach, doOnError, doOnLifeCycle, doOnNext, doOneSubscribe, doOnTerminate
		final Observable<Integer> observable = Observable.range(0, 4);
		observable.doOnEach(integerNotification -> System.out.println("on each: " + integerNotification.getValue())).subscribe(new PrintObserver("Each"));
		observable.doAfterNext(integer -> System.out.println("After next: " + integer)).subscribe(new PrintObserver("AfterNext"));
	}

	private static void materialize() {
		System.out.println("--- materialize/dematerialize");
		final Observable<Integer> observable = Observable.range(0, 4);
		observable.materialize().subscribe(new PrintObserver("Materialize"));
		observable.materialize().dematerialize().subscribe(new PrintObserver("Materialize-Dematerialize"));
	}

	private static void serialize() {
		System.out.println("--- serialize");
		final Observable<Integer> observable = Observable.range(0, 4);
		// FIXME Implement
		//observable.serialize()
	}

	private static void subscribe() {
		System.out.println("--- subscribe");
		// Method variants: subscribe(Observer), subscribe(Consumer), subscribe(Consumer, Consumer, Consumer), subscribe(Consumer, Consumer, Consumer)
		final Observable<Integer> observable = Observable.range(0, 4);
		observable.subscribe(new PrintConsumer(), new PrintConsumer());
		observable.subscribe(new PrintObserver());
	}

	private static void observerOn() {
		System.out.println("--- observerOn");
		// Some schedulers: io(), computation()
		// FIXME Add PrintObserver labeled with thread id
		// FIXME Compare it with thread this thread id
		final Observable<Integer> observable = Observable.range(0, 4);
		observable.observeOn(Schedulers.io()).subscribe(new PrintObserver());
	}

	private static void subscribeOn() {
		System.out.println("--- subscribeOn");
		// Some schedulers: io(), computation()
		// FIXME Add PrintObserver labeled with thread id
		// FIXME Compare it with thread this thread id
		final Observable<Integer> observable = Observable.range(0, 4);
		observable.subscribeOn(Schedulers.io()).subscribe(new PrintObserver());
	}

	private static void timeInterval() throws InterruptedException {
		System.out.println("--- timeInterval");
		// Method variants: timeInterval(), timeInterval(TimeUnit), timeInterval(Scheduler), timeInterval(TimeUnit, Scheduler)
		final Subject<Integer> observable = PublishSubject.create();
		observable.timeInterval().subscribe(new PrintObserver());

		observable.onNext(1);
		Thread.sleep(10);
		observable.onNext(2);
		observable.onNext(3);
		Thread.sleep(25);
		observable.onNext(4);
	}

	private static void timeout() throws InterruptedException {
		System.out.println("--- timeout");
		// Method variants: timeout(long, TimeUnit), timeout(long, TimeUnit, ObservableSource),  timeout(long, TimeUnit, Scheduler),  timeout(long, TimeUnit, Scheduler, ObservableSource), ...
		final Subject<Integer> observable = PublishSubject.create();
		observable.timeout(20, TimeUnit.MILLISECONDS).subscribe(new PrintObserver());
		observable.onNext(1);
		Thread.sleep(10);
		observable.onNext(2);
		observable.onNext(3);
		Thread.sleep(25);
		observable.onNext(4);
	}

	private static void timestamp() {
		System.out.println("--- timestamp");
		// Method variants: timestamp, timestamp(timeUnit), timestamp(scheduler), timestamp(timeUnit, scheduler)
		final Observable<Integer> observable = Observable.range(0, 4);
		observable.timestamp().subscribe(new PrintObserver());
	}

	private static void using() {
		System.out.println("--- using");
		// Method variants
		final Observable<Integer> observable = Observable.range(0, 4);
		// FIXME Implement
		//observable.using(, , )
	}
}
