# Strings (interview)

**Goal:** anagrams, palindrome, sliding window on chars, KMP ideas, in-place transforms.

**Package:** [com.interview.string](https://github.com/sys32805/learning-hub/tree/main/03-dsa-and-interview/algorithms/src/com/interview/string)

## Patterns

```java
// Frequency map
int[] freq = new int[26];
for (char c : s.toCharArray()) freq[c - 'a']++;

// Sliding window unique chars
Map<Character, Integer> last = new HashMap<>();
int left = 0, best = 0;
for (int right = 0; right < s.length(); right++) {
  char c = s.charAt(right);
  if (last.containsKey(c) && last.get(c) >= left) left = last.get(c) + 1;
  last.put(c, right);
  best = Math.max(best, right - left + 1);
}
```

## Start with these files

| File | GitHub |
|------|--------|
| `LongestPalindromeSubstring.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/string/LongestPalindromeSubstring.java) |
| `LongestSubstringWithoutRepetingCharacter.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/string/LongestSubstringWithoutRepetingCharacter.java) |
| `LongestSubstringWithKDistinctCharacters.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/string/LongestSubstringWithKDistinctCharacters.java) |
| `GroupAnagramsTogether.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/string/GroupAnagramsTogether.java) |
| `AnagramOfFirstAsSubstring.java` | [Open](https://github.com/sys32805/learning-hub/blob/main/03-dsa-and-interview/algorithms/src/com/interview/string/AnagramOfFirstAsSubstring.java) |

## Related

- [Sliding window concept](03-dsa-and-interview/concepts/sliding-window.md)  
- Next: [linked-list.md](03-dsa-and-interview/algorithms/linked-list.md)
