package org.linesofcode.goodreadsscraper.scanner;

import org.linesofcode.goodreadsscraper.Book;
import org.linesofcode.goodreadsscraper.Context;

public class RatingScanner implements Scanner {
	@Override
	public boolean isApplicable(Context context) {
		if (context.getBook() == null) {
			return false;
		}

		return context.getLine().contains("Average rating:");
	}

	@Override
	public void apply(Context context) {
		context.jump(3);

		Double avgRating = Double.valueOf(context.getLine().trim());
		Book book = context.getBook();
		book.setAverageRating(avgRating);

		context.jump(2);

		String ratings = context.getLine().trim();
		ratings = ratings.replace("(", "")
			.replace("ratings)", "")
			.replace("rating)", "")
			.replace(",", "")
			.trim();

		book.setRatings(Integer.valueOf(ratings));

		System.out.println(String.format("Found %d ratings with average of %f", book.getRatings(), book.getAverageRating()));
	}
}
