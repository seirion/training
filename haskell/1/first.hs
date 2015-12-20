
my_foldl:: (b -> a -> b) -> b -> [a] -> b
my_foldl f base [] = base
my_foldl f base (x:xs) = my_foldl f (f base x) xs

my_reverse:: [a] -> [a]
my_reverse xs = foldl (\base x -> x:base) [] xs

my_zipWith:: (a -> b -> c) -> [a] -> [b] -> [c]
my_zipWith f [] _ = []
my_zipWith f _ [] = []
my_zipWith f (x:xs) (y:ys) = (f x y):(my_zipWith f xs ys)

my_iterate f x = f x:my_iterate f (f x)

my_iterate2 f x = scanl (\a _ -> f a) x [1..]

{- zipWith 을 이용하여 fibonacci 수열 -}
fib = 1:1:zipWith (+) fib (tail fib)

{- scanl 을 이용하여 fibonacci 수열 -}
fib2 = 1:scanl (+) 1 fib2

--my_foldl2 f base xs = last(scanl f base xs)
--my_scanl f base xs = reverse $ foldl (\acc b -> (f (head acc) x):acc) [base] xs

{- ex 9 -}
sieve (p:xs) = [x|x<-xs, x `mod` p /= 0]
prime = map head $ iterate sieve [2..]

{- merge sort -}
merge:: Ord a => [a] -> [a] -> [a]
merge [] ys = ys
merge xs [] = xs
merge xall@(x:xs) yall@(y:ys) =
    if (x < y)
    then x:merge xs yall
    else y:merge xall ys

mergeSort:: Ord a => [a] -> [a]
mergeSort [] = []
mergeSort [a] = [a]
mergeSort xs = merge firstHalf lastHalf
               where firstHalf = mergeSort $ take (length xs `div` 2) xs
                     lastHalf = mergeSort $ drop (length xs `div` 2) xs

{- Binrary tree -}

data BinTree a = Empty | Fork a (BinTree a) (BinTree a) deriving Show
myTree = Fork 'a' (Fork 'b' Empty Empty) (Fork 'c' Empty (Fork 'd' Empty Empty))
myTree2 = Fork 1 (Fork 2 Empty Empty) (Fork 3 Empty (Fork 4 Empty Empty))

--class Functor f where
--    fmap : (a -> b) -> f a -> f b

instance Functor BinTree where
    fmap f Empty = Empty
    fmap f (Fork a l r) = Fork (f a) (fmap f l) (fmap f r)


{- -}
data RoseTree a = Branch a [RoseTree a] deriving Show

instance Functor RoseTree where
    fmap f (Branch a ts) = Branch (f a) (map (fmap f) ts)


{- fold 구현하기 -}
foldBinTree f base Empty = base
foldBinTree f base (Fork a l r) = f a v
    where v = foldBinTree f i l
          i = foldBinTree f base r


type Forest a = [RoseTree a]
foldtree:: (a -> b -> c) -> ([c] -> b) -> RoseTree a -> c
foldtree f g (Branch a ts) = f a v
    where v = foldforest f g ts

foldforest:: (a -> b -> c) -> ([c] -> b) -> Forest a -> b
foldforest f g ts = g (map (foldtree f g) ts)


{- foldable
instance Foldable BinTree where
    foldMap f Empty = mempty
    foldMap f (Fork a l r) = f a `mappend` (foldMap f l) `mappend` (foldMap f r)
-}
--instance Foldable RoseTree where
--    foldMap f (Branch a ts) = ?

my_maximum:: Ord a => [a] -> a
my_maximum xs = foldl1 max xs


my_partition :: (a -> Bool) -> [a] -> ([a], [a])
my_partition p xs = (filter p xs, filter (not . p) xs)

