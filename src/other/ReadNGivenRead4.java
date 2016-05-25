package other;

/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * The return value is the actual number of characters read. For example, it
 * returns 3 if there is only 3 characters left in the file.
 * By using the read4 API, implement the function int read(char *buf, int n)
 * that reads n characters from the file.
 * Note: The read function will only be called once for each test case.
 * Tags:
 */

/**
 * 这道题的题意有歧义
 * read4的函数不仅会返回读了几个字符  还会把字符读进你传得那个参数里
 * 然后要把这些读出来的字符 存进buf里 并且返回一共读了多少字符
 */
class ReadNGivenRead4 {
    public static void main(String[] args) {
        char[] buf = { 's', 'e', 't', 's', 'e', 't' };
        int n = 2;
        System.out.println(read(buf, n));
    }

    /**
     * Call only 1 time
     * Can reach n or end of file
     * if read4 returns size smaller than 4, it means end of file
     * the # of bytes read is the minimum of n - readBytes and size of read4
     * Copy things in read4 buffer to output buffer
     * Update readBytes
     */
    public static int read(char[] buf, int n) {
        char[] buffer = new char[4];
        int readBytes = 0;
        boolean eof = false; // flag
        while (!eof && readBytes < n) {
            int size = read4(buffer); // read4 is given
            if (size < 4)
                eof = true; // file end
            int bytes = Math.min(n - readBytes, size); // reach n or end of file
            // src, src pos, dest, dest pos, length
            System.arraycopy(buffer, 0, buf, readBytes, bytes); // copy to 
            readBytes += bytes;
        }
        return readBytes; // can be n or smaller
    }

    public static int read4(char[] buffer) {
        return 1;
    }
}
