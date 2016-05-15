package adesign;

/**
 * Created by shanshan on 16/5/15.
 */
public class ShortenURL {

/**The point of those functions is that they have an inverse function. This means you can have both encode() and decode() functions.
 * The steps are, therefore: (1) Save URL in database (2) Get unique row ID for that URL from database
 * (3) Convert integer ID to short string with encode(), e.g. 273984 to f5a4 (4) Use the short string (e.g. f4a4) in your sharable URLs
 *
 * (5) When receiving a request for a short string (e.g. 20a8),
 * decode the string to an integer ID with decode() (6) Look up URL in database for given ID. For conversion*/
}
