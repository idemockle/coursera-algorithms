# -*- coding: utf-8 -*-
"""
Created on Fri Jan 27 10:25:01 2017

@author: Ian
"""

def sort(inlist):
    aux = [None]*len(inlist)
    _sort(inlist, 0, len(inlist)-1, aux)
    
def _sort(inlist, lo, hi, aux):
    if hi <= lo:
        return
    mid = lo + (hi - lo)/2
    _sort(inlist, lo, mid, aux)
    _sort(inlist, mid + 1, hi, aux)
    _merge(inlist, lo, mid, hi, aux)
    
def _merge(inlist, lo, mid, hi, aux):
    i = lo
    j = mid + 1
    aux[lo:hi+1] = inlist[lo:hi+1]
    for k in xrange(lo, hi+1):
        if i > mid:
            inlist[k] = aux[j]
            j += 1
        elif j > hi:
            inlist[k] = aux[i]
            i += 1
        elif aux[j] < aux[i]:
            inlist[k] = aux[j]
            j += 1
        else:
            inlist[k] = aux[i]
            i += 1
    print inlist


import random
a= range(5,0,-1)
print a
sort(a)