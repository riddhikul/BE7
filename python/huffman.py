class Node:
   def __init__(self, left=None, right=None, value=None, frequency=None):
       self.left = left
       self.right = right
       self.value = value
       self.frequency = frequency

   def children(self):
       return (self.left, self.right)

class Huffman_Encoding:
   def __init__(self, string):
       self.q = []
       self.string = string
       self.encoding = {}
       self.encoded_text = ""
       self.decoded_text = ""

   def char_frequency(self):
       count = {}
       for char in self.string:
           if char not in count:
               count[char] = 0
           count[char] += 1
       
       for char, value in count.items():
           node = Node(value=char, frequency=value)
           self.q.append(node)
       self.q.sort(key=lambda x: x.frequency)

   def build_tree(self):
       while len(self.q) > 1:
           n1 = self.q.pop(0)
           n2 = self.q.pop(0)
           node = Node(left=n1, right=n2, frequency=n1.frequency + n2.frequency)
           self.q.append(node)
           self.q.sort(key=lambda x: x.frequency)

   def helper(self, node: Node, binary_str=""):
       if type(node.value) is str:
           self.encoding[node.value] = binary_str
           return
       
       l, r = node.children()
       self.helper(node.left, binary_str + "0")
       self.helper(node.right, binary_str + "1")
       return

   def huffman_encoding(self):
       root = self.q[0]
       self.helper(root, "")

   def encode_text(self):
       self.encoded_text = ''.join(self.encoding[char] for char in self.string)

   def decode_text(self):
       root = self.q[0]
       current = root
       self.decoded_text = ""
       
       for bit in self.encoded_text:
           if bit == '0':
               current = current.left
           else:
               current = current.right
               
           if current.value is not None:
               self.decoded_text += current.value
               current = root

   def print_encoding(self):
       print('\n=== Huffman Encoding Results ===')
       print('\nCharacter Frequency Table:')
       print(' Char | Huffman code ')
       print('-' * 20)
       for char, binary in self.encoding.items():
           print(" %-4r |%12s" % (char, binary))
       
       print('\nOriginal text:', self.string)
       print('Encoded bits:', self.encoded_text)
       print('Decoded text:', self.decoded_text)

   def encode(self):
       self.char_frequency()
       self.build_tree()
       self.huffman_encoding()
       self.encode_text()
       self.decode_text()
       self.print_encoding()

# Main execution
string = input("Enter string to be encoded: ")
encode = Huffman_Encoding(string)
encode.encode()