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
