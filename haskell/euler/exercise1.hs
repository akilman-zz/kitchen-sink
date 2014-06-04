-- Work in progress
let multiples = [ x | x <- [1..100], x `mod` 3 == 0, x `mod` 5 == 0 ]
