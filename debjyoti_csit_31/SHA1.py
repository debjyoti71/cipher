import hashlib

# The string to hash

input_string = "hello world"

# 1. Encode the string into bytes
# 2. Use hashlib.sha1() to create a hash object
# 3. Use .hexdigest() to get the string representation of the hash

sha1_hash = hashlib.sha1(input_string.encode('utf-8')).hexdigest()

print(f"SHA-1 hash for '{input_string}': {sha1_hash}")