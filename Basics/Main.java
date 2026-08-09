package Basics;

import java.util.*;

public class Main {

	// to sort char in str also for num String
	public static String sortString(String s) {

		// 1.convert it into char array
		// 2. sort them and change them to str.
		char[] ch = s.toCharArray();
		Arrays.sort(ch);
		String ans = new String(ch);

		return ans;
	}

	public static void handling() {
		try {
			int a = 9;
			int ans = a / 4;
			System.out.println(ans);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.print("Code is executed");
		}
	}

	public static int splitArray(int[] arr) {
		ArrayList<Integer> odd = new ArrayList<>();
		ArrayList<Integer> even = new ArrayList<>();

		for (int i = 0; i < arr.length; i++) {
			int digit = arr[i];
			if (digit % 2 == 0) {
				even.add(digit);
			} else {
				odd.add(digit);
			}
		}

		int max = Math.max(odd.size(), even.size());

		return odd.size() == even.size() ? odd.size() : max;
	}

	// check number is prime or not.
	public static boolean isPrime(int n) {
		if (n <= 1)
			return false;
		if (n == 2)
			return true;
		if (n % 2 == 0)
			return false;
		for (int i = 3; i <= Math.sqrt(n); i += 2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	// print primes in range
	public static void printPrimes(int n) {

		for (int i = 0; i <= n; i++) {
			if (isPrime(i)) {
				System.out.print(i + " ");
			}
		}
	}

	// check the anagram of a given string.
	public static boolean isAnagram(String s1, String s2) {

		if (s1.length() != s2.length())
			return false;

		int[] count = new int[26];

		for (int i = 0; i < s1.length(); i++) {
			count[s1.charAt(i) - 'a']++;
			count[s2.charAt(i) - 'a']--;
		}

		for (int x : count) {
			if (x != 0)
				return false;
		}

		return true;
	}

	// 12 factors is 1,2,3,6,12 here primes are 2, 3
	// by using prime numbers u need to multiply to get that number
	// cause 2 * 2 * 3 = 12 this is called factorization
	public static List<Integer> primeFactors(int[] arr) {
		int n = arr.length;
		ArrayList<Integer> primeFactors = new ArrayList<>();
		for (int num : arr) {

			// keep on divide by 2 if divides add to result and check again
			while (num % 2 == 0) {
				primeFactors.add(2);
				num /= 2;
			}

			// now do that same for all other prime numbers
			for (int i = 3; i * i <= num; i += 2) {
				while (num % i == 0) {
					primeFactors.add(i);
					num /= i;
				}
			}

			if (num > 2)
				primeFactors.add(num);
		}

		return primeFactors;
	}

	private static int precedence(char ch) {
		if (ch == '+' || ch == '-') {
			return 1;
		} else if (ch == '*' || ch == '/') {
			return 2;
		} else if (ch == '^') {
			return 3;
		}
		return -1;
	}

	public static String toPostFix(String str) {

		StringBuilder sb = new StringBuilder();
		Stack<Character> st = new Stack<>();

		for (char ch : str.toCharArray()) {

			if (Character.isLetterOrDigit(ch)) {
				sb.append(ch);
			} else if (ch == '(') {
				st.push(ch);
			} else if (ch == ')') {

				while (!st.isEmpty() && st.peek() != '(') {
					sb.append(st.pop());
				}
				st.pop();
			} else {
				while (!st.isEmpty() && precedence(ch) <= precedence(st.peek())) {
					sb.append(st.pop());
				}
				st.push(ch);
			}
		}

		while (!st.isEmpty()) {
			sb.append(st.pop());
		}
		return sb.toString();
	}

	// post fix evalution
	public static int postfixEvalution(String str) {

		Stack<Integer> st = new Stack<>();

		for (char ch : str.toCharArray()) {

			if (Character.isDigit(ch)) {
				st.push(ch - '0');
			} else {

				int b = st.pop();
				int a = st.pop();

				switch (ch) {
					case '+':
						st.push(a + b);
						break;
					case '-':
						st.push(a - b);
						break;
					case '*':
						st.push(a * b);
						break;
					case '/':
						st.push(a / b);
						break;
					default:
						break;
				}
			}
		}
		return st.pop();
	}

	// for prefix evalution iterate from right to left
	public static int prefixEvalution(String str) {

		Stack<Integer> st = new Stack<>();

		for (int i = str.length() - 1; i >= 0; i--) {
			char ch = str.charAt(i);
			if (Character.isDigit(str.charAt(i))) {
				st.push(str.charAt(i) - '0');
			} else {

				int b = st.pop();
				int a = st.pop();

				switch (ch) {
					case '+':
						st.push(a + b);
						break;
					case '-':
						st.push(a - b);
						break;
					case '*':
						st.push(a * b);
						break;
					case '/':
						st.push(a / b);
						break;
					default:
						break;
				}
			}
		}
		return st.pop();
	}

	public static String toPreFix(String str) {

		// 1. reverse given expre
		StringBuilder reversed = new StringBuilder(str).reverse();

		// 2. swap the brackets
		for (int i = 0; i < reversed.length(); i++) {
			if (reversed.charAt(i) == '(') {
				reversed.setCharAt(i, ')');
			} else if (reversed.charAt(i) == ')') {
				reversed.setCharAt(i, '(');
			}
		}

		// 3. convert it into postfix
		StringBuilder sb = new StringBuilder();
		String ans = toPostFix(reversed.toString());
		sb.append(ans);

		return sb.reverse().toString();
	}

	public static int gcd(int a, int b) {
		while (b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}

	public static int lcm(int a, int b) {
		int ans = (a * b) / gcd(a, b);
		return ans;
	}

	public static void fibonacci(int n) {
		if (n <= 0)
			return;

		int first = 0;
		int sec = 1;

		System.out.print(first + " ");
		if (n == 1)
			return;
		System.out.print(sec + " ");

		for (int i = 3; i <= n; i++) {
			int next = first + sec;
			System.out.print(next + " ");

			first = sec;
			sec = next;
		}
	}

	// sum until the single digit
	public static int sumUntilSingleDigit(int a) {
		int n = a;
		int sum = 0;
		while (n > 9) {
			sum = 0;
			while (n != 0) {
				int digit = n % 10;
				sum += digit;
				n /= 10;
			}
			n = sum;
		}
		return sum;
	}

	// reverse a num
	public static int reverseInt(int n) {
		int a = n;
		int reverse = 0;
		while (a != 0) {
			int digit = a % 10;
			reverse = reverse * 10 + digit;
			a /= 10;
		}
		return reverse;
	}

	public static void hashmap() {
		java.util.HashMap<String, Integer> map = new java.util.HashMap<>();
		map.put("slkdjf", 76);
		map.put("kkshdf", 56);
		map.put(",wiejdf", 38);
		map.put("ddkkd", 67);

		int min = Integer.MIN_VALUE;
		String ans = "";
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			int val = entry.getValue();
			if (val > min) {
				min = val;
				ans = entry.getKey();
			}
		}
	}

	public static void priorityqueue() {
		int[] arr = { 34, 56, 12, 1, 7, 9, 4, 23, 2, 89, 54 };
		// default min heap
		// max heap
		PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
		for (int i = 0; i < arr.length; i++) {
			pq.add(arr[i]);
		}
		System.out.print(pq.peek());
		pq.add(123);
		System.out.print(pq.peek());
	}

	// finding single missing number using xor
	// why xor? same val gives 0 the remaining stays in var return it.
	public static int findMissingNumber(int[] arr) {

		int n = arr.length + 1;
		int xor = 0;

		// XOR numbers from 1 to n
		for (int i = 1; i <= n; i++) {
			xor ^= i;
		}

		// XOR array elements
		for (int num : arr) {
			xor ^= num;
		}

		return xor;
	}

	// reverse the string
	public static String reverseString(String str) {

		char[] arr = str.toCharArray();

		int left = 0;
		int right = arr.length - 1;

		while (left < right) {

			char temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;

			left++;
			right--;
		}

		return new String(arr);
	}

	// binary search
	public static int binarySearch(int[] arr, int target) {

		int left = 0;
		int right = arr.length - 1;

		while (left <= right) {

			int mid = left + (right - left) / 2;

			if (arr[mid] == target) {
				return mid;
			}

			else if (arr[mid] < target) {
				left = mid + 1;
			}

			else {
				right = mid - 1;
			}
		}

		return -1;
	}

	public static void generateSubStrings(String a) {
		int n = a.length();
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				System.out.println(a.substring(i, j + 1));
			}
		}
	}

	public static void printSubsequence(String s, int index, String current) {

		if (index == s.length()) {
			System.out.println(current);
			return;
		}

		// Take current character
		printSubsequence(s, index + 1, current + s.charAt(index));

		// Don't take current character
		printSubsequence(s, index + 1, current);
	}

	// logic is simple
	// bigger then small means just add
	// III - 1+1+1 = 3, VIII - 5+1+1+1 = 8
	// smaller comes begore bigger subtract
	// IV = 5-1 = 4, XL = 50-10 = 40
	public static int romanToInt(String s) {

		HashMap<Character, Integer> map = new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);

		int ans = map.get(s.charAt(s.length() - 1));
		for (int i = s.length() - 2; i >= 0; i--) {

			int curr = map.get(s.charAt(i));
			int next = map.get(s.charAt(i + 1));

			// if curr char is < next
			if (curr < next) {
				ans -= curr; // sub
			} else {
				ans += curr; // add for greater
			}
		}

		return ans;
	}

	public static String intToRoman(int num) {

		String[] thousands = { "", "M", "MM", "MMM" };
		String[] hundreds = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
		String[] tens = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
		String[] ones = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };

		return thousands[num / 1000] + hundreds[(num % 1000) / 100] + tens[(num % 100) / 10] + ones[num % 10];

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// to convert a string 2d array to 2d array
		// "[1,2,3]"
		// "[4,5,6]"
		// "[7,8,9]"
		/*
		 * int n = Integer.parseInt(sc.nextLine());
		 * int[][] arr = new int[n][n];
		 * 
		 * // Read matrix
		 * for (int i = 0; i < n; i++) {
		 * String row = sc.nextLine();
		 * row = row.replace("[", "").replace("]", "");
		 * String[] parts = row.split(" ");
		 * 
		 * for (int j = 0; j < n; j++) {
		 * arr[i][j] = Integer.parseInt(parts[j]);
		 * }
		 * }
		 * 
		 * for (int[] ar : arr) {
		 * System.out.println(Arrays.toString(ar));
		 * }
		 */

		printPrimes(20);

	}

}