class Deque(object):
    
    def __init__(self):
        self.first = None
        self.last = None
        self.N = 0
        
    def isEmpty(self):
        return self.N == 0
    
    def size(self):
        return self.N
    
    def addFirst(self, item):
        oldfirst = self.first
        self.first = self._Node()
        self.first.next = oldfirst
        self.first.item = item
        if self.N == 0:
            self.last = self.first
        else:
            oldfirst.prev = self.first
        self.N += 1
    
    def addLast(self, item):
        oldlast = self.last
        self.last = self._Node()
        self.last.prev = oldlast;
        self.last.item = item
        if self.N == 0:
            self.first = self.last
        else:
            oldlast.next = self.last
        self.N += 1
    
    def removeFirst(self):
        oldfirst = self.first;
        self.first = self.first.next;
        if self.N == 1:
            self.last = None
        else:
            self.first.prev = None
        self.N -= 1
        return oldfirst.item
        
    def removeLast(self):
        oldlast = self.last
        self.last = self.last.prev
        if self.N == 1:
            self.first = None
        else:
            self.last.next = None
        self.N -= 1
        return oldlast.item
        
    class _Node(object):
    
        def __init__(self):
            self.next = None
            self.prev = None
            self.item = None