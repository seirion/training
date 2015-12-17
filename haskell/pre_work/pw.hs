my_filter:: (a -> Bool) -> [a] -> [a]
my_filter f [] = []
my_filter f (x:xs) = if (f x)
                     then x:(my_filter f xs)
                     else my_filter f xs

{-
	숙제1) foldr 함수를 이용해서 sum 함수를 직접 구현해보세요.
	> let my_sum:: Num a => [a] -> a; my_sum xs = foldr (+) 0 xs
-}
my_sum:: Num a => [a] -> a
my_sum xs = foldr (+) 0 xs

{-
	숙제2) foldr 함수를 이용해서 map 함수를 직접 구현해보세요.
-}
my_map:: (a -> b) -> [a] -> [b]
my_map f xs = foldr (\x base -> (f x):base) [] xs

{-
	숙제3) foldr 함수를 재귀적으로 직접 구현해보세요.
-}
my_foldr:: (a -> b -> b) -> b -> [a] -> b
my_foldr f base [] = base
my_foldr f base (x:xs) = f x (my_foldr f base xs)
