package cz.novros.reactiveworld.observable;

import cz.novros.reactiveworld.observer.PrintObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

public class ObservableTransformation {

	public static void main(final String[] args) {
		System.out.println("Group functions:");
		buffer();
		window();
		groupBy();
		System.out.println("Mapping functions:");
		map();
		flatMap();
		scan();
	}

	public static void buffer() {
		System.out.println("ObservableTransformation.buffer");
		System.out.println("-------------------------------");
		final Observable<Integer> observable = Observable.range(0, 10);
		observable.buffer(2, 4).subscribe(new PrintObserver());
	}

	public static void window() {
		System.out.println("ObservableTransformation.window");
		System.out.println("-------------------------------");
		final Observable<Integer> observable = Observable.range(0, 10);
		final Observer<Object> observer = new PrintObserver();
		observable.window(2, 4)
				.subscribe(integerObservable -> integerObservable.subscribe(observer));
	}

	public static void groupBy() {
		System.out.println("ObservableTransformation.groupBy");
		System.out.println("-------------------------------");
		final Observable<Integer> observable = Observable.range(0, 10);
		observable.groupBy(integer -> integer % 3)
				.subscribe(grouped -> {
					System.out.println("Accepting group: " + grouped.getKey());
					grouped.subscribe(new PrintObserver(grouped.getKey().toString()));
				});
	}

	private static void map() {
		System.out.println("ObservableTransformation.map");
		System.out.println("-------------------------------");
		final Observable<Integer> observable = Observable.range(0, 5);
		observable.map(integer -> integer.doubleValue() / 100).subscribe(new PrintObserver());
	}

	private static void flatMap() {
		System.out.println("ObservableTransformation.flatMap");
		System.out.println("-------------------------------");
		final Observable<Integer> observable = Observable.range(0, 5);
		observable.flatMap((Function<Integer, ObservableSource<?>>) integer -> Observable.range(0, integer))
				.subscribe(new PrintObserver());
	}

	private static void scan() {
		System.out.println("ObservableTransformation.scan");
		System.out.println("-------------------------------");
		final Observable<Integer> observable = Observable.range(0, 5);
		observable.scan(10, (integer, integer2) -> integer + integer2).subscribe(new PrintObserver());
	}
}
