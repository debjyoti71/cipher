import hashlib

# The string to hash

input_string = "hello world"

# Encode the string to bytes using utf-8 (or another encoding)

encoded_string = input_string.encode('utf-8')

# Create an MD5 hash object and update it with the encoded string

md5_hash = hashlib.md5(encoded_string)

# Get the hash in hexadecimal string format

hex_digest = md5_hash.hexdigest()

# Print the result

print(f"MD5 Hash of '{input_string}': {hex_digest}")