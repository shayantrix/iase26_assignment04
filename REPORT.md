# Fuzzing report: iase26 Assignment 04

Name(s) and student ID:

1- Seyed Shayan Amir Shahkarami

Student ID: 4767208

2- Siavash Sharifnezhad Ahvazi

Student ID: 4767207

## Platform tested

(The OS and architecture you ran the fuzzer on, e.g. macOS 14 arm64, Ubuntu 22.04 x86_64,
Windows 11 x86_64.)

Shayan's : Linux fedora.fritz.box 7.0.11-100.fc43.x86_64

Siavash's : Windows 11 x86_64

## Exercise 1: mutational fuzzer

The crash exit codes you found. For each exit code, give one representative input (or how to
generate it). Crashing inputs are grouped under `output/crashes/exit<code>/`, so the exit code is the
directory name. One representative per exit code is enough.

1. Exit code 134 (`SIGABRT`). Representative input:
   `output/crashes/exit134/40e99026338245ffc84c05f26678d20cbc44b48eecd3c4c6697157ebc7de4a10/input`

   ```toml
   greting="helllllllllllo world"
   words=["a", "b", "c"]
   mati=[["a",,,,("b"],z ["c", "d"]]
   ```

2. Exit code 139 (`SIGSEGV`). Representative input:
   `output/crashes/exit139/0007cc246ce42674abd5f6300fd57f1a1ef9322e6031c1b8cefac39d9eab86ce/input`

   ```toml
   greeting="hello world2
   worrrds=["a", "tb", "c"]
   matrix=[["a", "b"], ["c", "d"]]
   ```

## Exercise 2: grammar-based fuzzer

Which crash does the grammar-based fuzzer reach? Why can neither a mutational nor a lexical
(regular) fuzzer reach it?

The grammar-based fuzzer reaches the deep-recursion crash in the TOML parser. With
`--grammar-recursive-depth 300 --grammar-recursion-strategy linear`, it generated a syntactically
valid TOML file whose value is a very deeply nested array. On Siavash's Windows run, the target was
`toml_parser_win_x86_64.exe` and the fuzzer found the crash in the first execution. Windows reported
the crash as exit code `-1073740791`, which corresponds to `0xC0000409`.

The input has this shape:

```toml
9=""
h=["", [[["", ["", ... many nested arrays ... ,""]]]]]
```

So the important part is not a strange character or an invalid token, but the valid recursive array
structure. The parser accepts the syntax and only crashes after it follows the nested
`value -> array -> value -> array ...` structure too deeply.

A mutational fuzzer is very unlikely to reach this crash because it starts from small seed files and
only performs local edits such as inserting, deleting, flipping, or repeating characters. To trigger
this bug, it would have to create hundreds of matching `[` and `]` brackets while also keeping the
commas and values in the right places. Almost all random local edits that try to increase the depth
would break the TOML syntax first, so the input would be rejected before reaching the vulnerable
parser path.

A lexical or regular fuzzer also cannot reach this bug in the general case because the trigger is
context-free nesting. A regular generator can produce tokens such as brackets, commas, keys, and
strings, but it does not remember an unbounded number of opened arrays and close them correctly.
Balanced recursive arrays require a grammar with recursion, which is exactly what the grammar-based
fuzzer has and what the regular fuzzer lacks.
