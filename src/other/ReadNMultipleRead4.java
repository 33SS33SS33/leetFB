package other;

/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * The return value is the actual number of characters read. For example,
 * it returns 3 if there is only 3 characters left in the file.
 * By using the read4 API, implement the function int read(char *buf, int n)
 * that reads n characters from the file.
 * Similar to Read N Characters Given Read4, but the read
 * function may be called multiple times
 * Tags:
 */

/**
 * 和上道题基本一样 这里要处理的问题是 如果一个字符串长度是5
 * 然后他第一次read(3) 然后又read(2) 这样的话 其实第一次是读了4个
 * 所以要把第一次剩下的那个留第二次的用 所以用了个全局的queue来处理 其他基本一样
 */
class ReadNMultipleRead4 {
    public static void main(String[] args) {
        char[] buf = { 's', 'e', 't', 's', 'e', 't' };
        int n = 2;
        System.out.println(new ReadNMultipleRead4().read(buf, n));
    }

    /**
     * Store state of previous call
     * Including a buffer, an offset index of the buffer,
     */
    private char[] buffer = new char[4];
    int offset = 0, bufsize = 0;

    /**
     * Call multiple times, storing states
     * The difference between single time multiple times is:
     * There can be some characters return by read4 in the buffer
     * We need to get them for the next call
     * For example, supppose n is 5, read4 will be call twice, 3 chars remain
     * Next read5 call needs to get those 3 characters
     * So we make buffer as a field variable, along with offset and bufsize
     * If bufsize > 0, means something in buffer
     *
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return The number of characters read
     */
    public int read(char[] buf, int n) {
        int readBytes = 0;
        boolean eof = false; // flag
        while (!eof && readBytes < n) {
            int sz = (bufsize > 0) ? bufsize : read4(buffer);
            if (bufsize == 0 && sz < 4)
                eof = true; // empty buffer, no more
            int bytes = Math.min(n - readBytes, sz);
            // from offset in buffer, to readBytes in output buffer
            System.arraycopy(buffer, offset, buf, readBytes, bytes);
            offset = (offset + bytes) % 4; // update offset
            bufsize = sz - bytes; // size read - size copied
            readBytes += bytes; // update readBytes
        }
        return readBytes;
    }

    public int read4(char[] buffer) {
        return 1;
    }
}
