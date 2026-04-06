import hashlib

# The string to be hashed

input_string = "hello world"

# Encode the string to bytes (UTF-8 is common)

bytes_string = input_string.encode('utf-8')

# Create a new SHA-512 hash object

hash_object = hashlib.sha512()

# Update the hash object with the bytes

hash_object.update(bytes_string)

# Get the hexadecimal representation of the hash

hex_digest = hash_object.hexdigest()

# Print the result

print(f"Original string: {input_string}")

print(f"SHA-512 hash: {hex_digest}")