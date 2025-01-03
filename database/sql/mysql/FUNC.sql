## 日期时间函数

1. 获取当前日期、时间


函数 CURRENT_DATE() / CURDATE() 用于获取系统的当前日期。



SELECT CURRENT_DATE(), CURDATE();
+----------------+------------+
| CURRENT_DATE() | CURDATE()  |
+----------------+------------+
| 2024-05-22     | 2024-05-22 |
+----------------+------------+


函数 CURRENT_TIME() / CURTIME() 用于获取系统的当前时间。



SELECT CURRENT_TIME(), CURTIME();
+----------------+-----------+
| CURRENT_TIME() | CURTIME() |
+----------------+-----------+
| 09:51:14       | 09:51:14  |
+----------------+-----------+


当需要同时获取当前的日期和时间信息时，MySQL 中有 4 个函数可供我们选择：CURRENT_TIMESTAMP()、LOCALTIME()、NOW() 和 SYSDATE() 。



SELECT CURRENT_TIMESTAMP(), LOCALTIME(), NOW(), SYSDATE();
+---------------------+---------------------+---------------------+---------------------+
| CURRENT_TIMESTAMP() | LOCALTIME()         | NOW()               | SYSDATE()           |
+---------------------+---------------------+---------------------+---------------------+
| 2024-05-22 09:53:28 | 2024-05-22 09:53:28 | 2024-05-22 09:53:28 | 2024-05-22 09:53:28 |
+---------------------+---------------------+---------------------+---------------------+


2. UNIX 时间戳函数


调用 UNIX_TIMESTAMP() 返回当前时间戳（即 1970 年 1 月 1 日 零点零分零秒后的秒数）。



SELECT UNIX_TIMESTAMP();
+------------------+
| UNIX_TIMESTAMP() |
+------------------+
|       1716383414 |
+------------------+


也可以传递一个表示日期时间的字符串，返回相应的秒数。



SELECT NOW(), UNIX_TIMESTAMP(NOW());
+---------------------+-----------------------+
| NOW()               | UNIX_TIMESTAMP(NOW()) |
+---------------------+-----------------------+
| 2024-05-22 13:11:35 |            1716383495 |
+---------------------+-----------------------+


函数 FROM_UNIXTIME(date) 与 UNIX_TIMESTAMP() 的作用刚好相反：把时间戳还原成日期时间格式的字符串。



SELECT FROM_UNIXTIME('1716383414');
+-----------------------------+
| FROM_UNIXTIME('1716383414') |
+-----------------------------+
| 2024-05-22 13:10:14.000000  |
+-----------------------------+


3. 获取 UTC 日期、时间


函数 UTC_DATE() 获取 UTC 日期，UTC_TIME() 获取 UTC 时间。



SELECT UTC_DATE(), UTC_TIME();
+------------+------------+
| UTC_DATE() | UTC_TIME() |
+------------+------------+
| 2024-05-22 | 13:16:18   |
+------------+------------+


这里的 UTC 日期时间之所以和上文的 NOW() 函数返回的本地日期时间一致，是因为小鱼的 MySQL 数据库的时区设置了跟随系统时区，而系统时区使用了 UTC 时区。



SHOW VARIABLES LIKE '%time_zone%';
+------------------+--------+
| Variable_name    | Value  |
+------------------+--------+
| system_time_zone | UTC    |
| time_zone        | SYSTEM |
+------------------+--------+


下面，我们将 MySQL 数据库的时区设置为东八区，这时 UTC_DATE 和 UTC_TIME 返回的时间将比 NOW() 慢 8 个小时。



SET time_zone = '+8:00';
Query OK, 0 rows affected (0.01 sec)

SELECT UTC_DATE(), UTC_TIME(), NOW();
+------------+------------+---------------------+
| UTC_DATE() | UTC_TIME() | NOW()               |
+------------+------------+---------------------+
| 2024-05-22 | 13:18:16   | 2024-05-22 21:18:16 |
+------------+------------+---------------------+


4. 获取月份信息 MONTH 和 MONTHNAME


函数 MONTH 返回月份对应的数字形式，范围为 1~12 ；MONTHNAME 返回月份对应的英文名称。



SELECT MONTH(NOW()), MONTHNAME(NOW());
+--------------+------------------+
| MONTH(NOW()) | MONTHNAME(NOW()) |
+--------------+------------------+
|            5 | May              |
+--------------+------------------+


5. 获取星期信息 DAYNAME DAYOFWEEK 和 WEEKDAY


函数 DAYNAME 返回一周中的星期几，星期的名称为英文形式。



SELECT NOW(), DAYNAME(NOW());
+---------------------+----------------+
| NOW()               | DAYNAME(NOW()) |
+---------------------+----------------+
| 2024-05-22 13:28:53 | Wednesday      |
+---------------------+----------------+


函数 DAYOFWEEK 则返回这一天在一周中的编号，范围为 1~7，分别对应周日~周六。



SELECT NOW(), DAYOFWEEK(NOW());
+---------------------+------------------+
| NOW()               | DAYOFWEEK(NOW()) |
+---------------------+------------------+
| 2024-05-22 13:30:39 |                4 |
+---------------------+------------------+


函数 WEEKDAY 也返回这一天在一周中的位置索引，但索引范围为 0~6，分别对应周一~周日。



SELECT NOW(), WEEKDAY(NOW());
+---------------------+----------------+
| NOW()               | WEEKDAY(NOW()) |
+---------------------+----------------+
| 2024-05-22 13:32:19 |              2 |
+---------------------+----------------+


6. 获取一年中的第几个星期 WEEK 和 WEEKOFYEAR


函数 WEEK(d) 返回日期 d 是一年中的第几周，范围为 0~53。



SELECT WEEK(NOW()), WEEK(NOW(), 1);
+-------------+---------------+
| WEEK(NOW()) | WEEK(NOW(),1) |
+-------------+---------------+
|          20 |            21 |
+-------------+---------------+


WEEK 有一个可选的参数 default_week_format ，默认为 0 ，即使用周日作为一周的开始。当指定为 1 时，使用周一作为一周的开始。



函数 WEEKOFYEAR(d) 返回的编号范围为 1~53，使用周一作为新一周的开始。



SELECT WEEK(NOW(), 3), WEEKOFYEAR(NOW());
+---------------+-------------------+
| WEEK(NOW(),3) | WEEKOFYEAR(NOW()) |
+---------------+-------------------+
|            21 |                21 |
+---------------+-------------------+


7. 获取一年中的第几天 DAYOFYEAR


函数 DAYOFYEAR(d) 返回日期 d 是一年中的第几天，范围为 1~366。



SELECT NOW(), DAYOFYEAR(NOW());
+---------------------+------------------+
| NOW()               | DAYOFYEAR(NOW()) |
+---------------------+------------------+
| 2024-05-23 02:09:53 |              144 |
+---------------------+------------------+


8. 获取一个月中的第几天 DAYOFMONTH


函数 DAYOFMONTH(d) 返回日期 d 是一个月中的第几天，范围为 1~31。



SELECT NOW(), DAYOFMONTH(NOW());
+---------------------+-------------------+
| NOW()               | DAYOFMONTH(NOW()) |
+---------------------+-------------------+
| 2024-05-23 02:10:01 |                23 |
+---------------------+-------------------+


9. 获取年份、季度以及时分秒信息


获取日期时间中的单独信息，其中 QUARTER(d) 用于获取季度信息。



SELECT NOW(), YEAR(NOW()), MONTH(NOW()), DAY(NOW()), QUARTER(NOW());
+---------------------+-------------+--------------+------------+----------------+
| NOW()               | YEAR(NOW()) | MONTH(NOW()) | DAY(NOW()) | QUARTER(NOW()) |
+---------------------+-------------+--------------+------------+----------------+
| 2024-05-23 10:17:57 |        2024 |            5 |         23 |              2 |
+---------------------+-------------+--------------+------------+----------------+

SELECT NOW(), HOUR(NOW()), MINUTE(NOW()), SECOND(NOW());
+---------------------+-------------+---------------+---------------+
| NOW()               | HOUR(NOW()) | MINUTE(NOW()) | SECOND(NOW()) |
+---------------------+-------------+---------------+---------------+
| 2024-05-23 10:17:08 |          10 |            17 |             8 |
+---------------------+-------------+---------------+---------------+


10. 时长与秒数的相互转换 TIME_TO_SEC 和 SEC_TO_TIME


函数 TIME_TO_SEC(time) 将时分秒转换为秒数，公式为：hour * 3600 + minute * 60 + second 。



SELECT TIME_TO_SEC('02:05:30');
+-------------------------+
| TIME_TO_SEC('02:05:30') |
+-------------------------+
|                    7530 |
+-------------------------+


函数 SEC_TO_TIME(seconds) 则刚好相反，将秒数转换为时、分、秒信息。



SELECT SEC_TO_TIME(7530);
+-------------------+
| SEC_TO_TIME(7530) |
+-------------------+
| 02:05:30          |
+-------------------+


11. 日期时间的运算


函数 DATE_ADD 和 ADDDATE 执行日期的加法运算：



SELECT NOW(), DATE_ADD(NOW(), INTERVAL 10 SECOND);
+---------------------+-------------------------------------+
| NOW()               | DATE_ADD(NOW(), INTERVAL 10 SECOND) |
+---------------------+-------------------------------------+
| 2024-05-23 08:48:27 | 2024-05-23 08:48:37                 |
+---------------------+-------------------------------------+

SELECT NOW(), ADDDATE(NOW(), INTERVAL '10:10' MINUTE_SECOND);
+---------------------+------------------------------------------------+
| NOW()               | ADDDATE(NOW(), INTERVAL '10:10' MINUTE_SECOND) |
+---------------------+------------------------------------------------+
| 2024-05-23 08:49:15 | 2024-05-23 08:59:25                            |
+---------------------+------------------------------------------------+


日期 2024-05-2308:48:27 增加 10s 后的日期为 2024-05-2308:48:37 。日期 2024-05-2308:49:15 增加 10 分钟零 10 秒为 2024-05-2308:59:25 。



类似的，函数 DATE_SUB 和 SUBDATE 用来执行日期时间的减法运算：



SELECT NOW(), DATE_SUB(NOW(), INTERVAL 10 SECOND);
+---------------------+-------------------------------------+
| NOW()               | DATE_SUB(NOW(), INTERVAL 10 SECOND) |
+---------------------+-------------------------------------+
| 2024-05-23 09:00:24 | 2024-05-23 09:00:14                 |
+---------------------+-------------------------------------+

SELECT NOW(), SUBDATE(NOW(), INTERVAL '10:10' MINUTE_SECOND);
+---------------------+------------------------------------------------+
| NOW()               | SUBDATE(NOW(), INTERVAL '10:10' MINUTE_SECOND) |
+---------------------+------------------------------------------------+
| 2024-05-23 09:01:35 | 2024-05-23 08:51:25                            |
+---------------------+------------------------------------------------+


上述函数用到的第二个参数可以是如下的表达式：



单位类型	表达式格式
MICROSECOND	INTERVAL 微妙数 MICROSECOND
SECOND	INTERVAL 秒数 SECOND
MINUTE	INTERVAL 分钟数 MINUTE
HOUR	INTERVAL 小时数 HOUR
DAY	INTERVAL 天数 DAY
WEEK	INTERVAL 星期数 WEEK
MONTH	INTERVAL 月 MONTH
QUARTER	INTERVAL 季度 QUARTER
YEAR	INTERVAL 年 YEAR
SECOND_MICROSECOND	INTERVAL '秒数.微秒数'
MINUTE_MICROSECOND	INTERVAL '分钟数.微妙数' SECOND_MICROSECOND
MINUTE_SECOND	INTERVAL '分钟数:秒数' MINUTE_SECOND
HOUR_MICROSECOND	INTERVAL '小时数.微妙数' HOUR_MICROSECOND
HOUR_SECOND	INTERVAL '小时数:分钟数:秒数' HOUR_SECOND
HOUR_MINUTE	INTERVAL '小时数:分钟数' HOUR_MINUTE
DAY_MICROSECOND	INTERVAL '天数.微妙数' DAY_MICROSECOND
DAY_SECOND	INTERVAL '天数 时:分:秒' DAY_MINUTE
DAY_MINUTE	INTERVAL '天数 时:分' DAY_MINUTE
DAY_HOUR	INTERVAL '天数 时' DAY_HOUR
YEAR_MONTH	INTERVAL '年-月' YEAR_MONTH


如果仅仅是时间的加减运算，可以使用 ADDTIME 和 SUBTIME 函数，可以无需传递复杂的表达式。



SELECT NOW(), ADDTIME(NOW(), '0:0:10'), SUBTIME(NOW(), '0:10:05');
+---------------------+--------------------------+---------------------------+
| NOW()               | ADDTIME(NOW(), '0:0:10') | SUBTIME(NOW(), '0:10:05') |
+---------------------+--------------------------+---------------------------+
| 2024-05-23 10:15:03 | 2024-05-23 10:15:13      | 2024-05-23 10:04:58       |
+---------------------+--------------------------+---------------------------+


关于日期时间的运算，还有一个函数 DATEDIFF(date1,date2) 返回日期 date1 和 date2 之间的天数。



SELECT NOW(), DATEDIFF(NOW(), '2024-04-10'), DATEDIFF('2024-04-10', NOW());
+---------------------+-------------------------------+-------------------------------+
| NOW()               | DATEDIFF(NOW(), '2024-04-10') | DATEDIFF('2024-04-10', NOW()) |
+---------------------+-------------------------------+-------------------------------+
| 2024-05-23 10:22:21 |                            43 |                           -43 |
+---------------------+-------------------------------+-------------------------------+


✍ 函数 DATEDIFF 计算间隔的天数时，仅使用日期不分完成计算。



SELECT DATE_SUB(CURDATE(), INTERVAL 86401 SECOND) AS yesterday_start;



SELECT DATE_SUB(CURDATE(), INTERVAL 1 SECOND) AS yesterday_end;


1. 字符计数函数 CHAR_LENGTH


调用 CHAR_LENGTH(str) 返回字符串 str 中字符的数量。



✍ 中文等多字节字符算作单个字符。



SELECT CHAR_LENGTH('中'), CHAR_LENGTH('Hello');
+-------------------+----------------------+
| CHAR_LENGTH('中') | CHAR_LENGTH('Hello') |
+-------------------+----------------------+
|                 1 |                    5 |
+-------------------+----------------------+


2. 计算字节长度 LENGTH


调用 LENGTH(str) 返回字符串 str 编码后的字节长度。




SELECT LENGTH('中'), LENGTH('Hello');
+--------------+-----------------+
| LENGTH('中') | LENGTH('Hello') |
+--------------+-----------------+
|            3 |               5 |
+--------------+-----------------+


对于汉字 中 函数 LENGTH 返回的字节长度是 3 ，这是因为 UTF-8 编码中保存汉字 中 需要 3 个字节 b'\xe4\xb8\xad' 。




SHOW VARIABLES LIKE 'character_set_%';
+--------------------------+--------------------------------+
| Variable_name            | Value                          |
+--------------------------+--------------------------------+
| character_set_client     | utf8mb4                        |
| character_set_connection | utf8mb4                        |
| character_set_database   | utf8mb4                        |
| character_set_filesystem | binary                         |
| character_set_results    | utf8mb4                        |
| character_set_server     | utf8mb4                        |
| character_set_system     | utf8mb3                        |
| character_sets_dir       | /usr/share/mysql-8.3/charsets/ |
+--------------------------+--------------------------------+


✍ 有关字符集编码以及 UTF-8 编码规则可以在小鱼的另外两篇文章中查看。


山药鱼儿：「字符集编码」编码的历史
1 赞同 · 0 评论文章

山药鱼儿：「字符集编码」Unicode 转化格式 - UTF-8
2 赞同 · 0 评论文章



3. 字符串合并 CONCAT CONCAT_WS


字符串函数 CONCAT 用来拼接多个字符串：




SELECT CONCAT('MySQL', '8.0'), CONCAT('Hello', 'World');
+------------------------+--------------------------+
| CONCAT('MySQL', '8.0') | CONCAT('Hello', 'World') |
+------------------------+--------------------------+
| MySQL8.0               | HelloWorld               |
+------------------------+--------------------------+


✍ 如果任何一个参数为 NULL 则 CONCAT 函数返回的结果也为 NULL 。


字符串拼接的另一个函数是 CONCAT_WS ，代表 CONCAT With Separator ，即待分隔符的拼接。




SELECT CONCAT_WS('-', 'MySQL', '8.0'), CONCAT(' ', 'Hello', 'World');
+--------------------------------+-------------------------------+
| CONCAT_WS('-', 'MySQL', '8.0') | CONCAT(' ', 'Hello', 'World') |
+--------------------------------+-------------------------------+
| MySQL-8.0                      |  HelloWorld                   |
+--------------------------------+-------------------------------+


✍ 分隔符作为第一个参数传入。


4. 替换函数
INSERT 字符串替换函数
INSERT 参数列表如下：



INSERT (s1, x, len, s2)


INSERT 函数对字符串 s1 中的内容进行替换，找到索引为 x 的位置，截取长度为 len 的子字符串，替换为 s2 ，返回替换后的内容。




SELECT INSERT('******', 2, 4, '@') as col1, ->
INSERT ('******', 3, 100, '@') as col2,
    ->
INSERT ('******', 7, 3, '@') as col3;
+------+------+--------+
| col1 | col2 | col3   |
+------+------+--------+
| *@*  | **@  | ****** |
+------+------+--------+


字符串 ****** 的索引范围为 1~6，对于字段 col1 从第二个 * 开始截掉 4 个 * 号，并替换为 @ 符号，因此结果为 *@* 。



对于字段 col2 ，由于长度 100 超出了索引右侧剩余可截取的范围，
INSERT 函数将截掉索引右侧的全部字符，并替换为 @ 符号，因此结果为 **@ 。



最后，对于字段 col3，由于起始位置的索引 7 超出了字符串 ****** 的索引范围，将直接返回原字符串。



5. 大小写转换函数 LOWER UPPER


函数 LOWER 或者 LCASE 将字符串中的大写字母全部转换为小写字母。




SELECT LOWER('Hello World!'), LCASE('Hello World!');
+-----------------------+-----------------------+
| LOWER('Hello World!') | LCASE('Hello World!') |
+-----------------------+-----------------------+
| hello world!          | hello world!          |
+-----------------------+-----------------------+


函数 UPPER 或者 UCASE 将字符串中的小写字母全部转换为大写字母。




SELECT UPPER('Hello World!'), UCASE('Hello World!');
+-----------------------+-----------------------+
| UPPER('Hello World!') | UCASE('Hello World!') |
+-----------------------+-----------------------+
| HELLO WORLD!          | HELLO WORLD!          |
+-----------------------+-----------------------+


6. 截取指定长度的字符串 LEFT 和 RIGHT


函数 LEFT(s,n) 返回字符串 s 左侧的 n 个字符。




SELECT LEFT('Hello', 3), LEFT('Hello', 10);
+------------------+-------------------+
| LEFT('Hello', 3) | LEFT('Hello', 10) |
+------------------+-------------------+
| Hel              | Hello             |
+------------------+-------------------+
函数 RIGHT(s,n) 返回字符串 s 右侧的 n 个字符。




SELECT RIGHT('Hello', 3), RIGHT('Hello', 0);
+-------------------+-------------------+
| RIGHT('Hello', 3) | RIGHT('Hello', 0) |
+-------------------+-------------------+
| llo               |                   |
+-------------------+-------------------+


7. 获取指定长度字符串 LPAD 和 RPAD


LPAD 在截取字符串时，若字符串的长度不够，则在左侧使用指定的字符进行填充。




SELECT LPAD('Hello', 3, '?'), LPAD('Hello', 10, '?');
+-----------------------+------------------------+
| LPAD('Hello', 3, '?') | LPAD('Hello', 10, '?') |
+-----------------------+------------------------+
| Hel                   | ?????Hello             |
+-----------------------+------------------------+


函数 RPAD 则是将填充的字符串组合在原字符串的右侧。




SELECT RPAD('Hello', 3, '?'), RPAD('Hello', 10, '?');
+-----------------------+------------------------+
| RPAD('Hello', 3, '?') | RPAD('Hello', 10, '?') |
+-----------------------+------------------------+
| Hel                   | Hello?????             |
+-----------------------+------------------------+


8. 删除两侧空格 LTRIM RTRIM 以及 TRIM


函数 LTRIM RTRIM 以及 TRIM 分别用于删除字符串左侧、右侧以及两侧的空白。




SELECT CONCAT('"', ' hello ', '"'), -> CONCAT('"', LTRIM(' hello '), '"') AS col1, -> CONCAT('"', RTRIM(' hello '), '"') AS col2, -> CONCAT('"', TRIM(' hello '), '"') AS col3;
+-----------------------------+----------+----------+---------+
| CONCAT('"', ' hello ', '"') | col1     | col2     | col3    |
+-----------------------------+----------+----------+---------+
| " hello "                   | "hello " | " hello" | "hello" |
+-----------------------------+----------+----------+---------+


9. 重复生成字符串 REPEAT 和 SPACE


函数 REPEAT(s,n) 生成一个由字符串 s 重复 n 次生成的字符串。




SELECT REPEAT('ol', 6), REPEAT(NULL, 6), REPEAT('ol', NULL);

+-----------------+-----------------+--------------------+
| REPEAT('ol', 6) | REPEAT(NULL, 6) | REPEAT('ol', NULL) |
+-----------------+-----------------+--------------------+
| olololololol    | NULL            | NULL               |
+-----------------+-----------------+--------------------+


✍ 函数
REPLACE 中的任何一个参数为 NULL 结果也为 NULL 。


    另外，字符串函数 SPACE(n) 返回由 n 个空格组成的字符串。


    
SELECT CONCAT('"', SPACE(6), '"');
+----------------------------+
| CONCAT('"', SPACE(6), '"') |
+----------------------------+
| "      "                   |
+----------------------------+


10. 字符串替换函数
REPLACE 函数
REPLACE (s, s1, s2) 查找字符串 s 中的所有 s1 子字符串并替换为 s2 字符串。


    
SELECT REPLACE('hello', 'l', '?');
+----------------------------+
|
REPLACE ('hello', 'l', '?') |
+----------------------------+
| he??o                      |
+----------------------------+


11. 获取子字符串 SUBSTRING 和 MID


函数 SUBSTRING(s,n,len) / MID(s,n,len) 用来截取字符串 s ：从第 n 开始，截取长度为 len 。



✍ 参数 len 可以省略，表示截取剩余全部。



SELECT MID('hello', 3, 3), MID('hello', 3), MID('hello', -3);
+--------------------+-----------------+------------------+
| MID('hello', 3, 3) | MID('hello', 3) | MID('hello', -3) |
+--------------------+-----------------+------------------+
| llo                | llo             | llo              |
+--------------------+-----------------+------------------+


当参数 n 为负数时，表示从字符串末尾第 n 位开始截取。



12. 查找子串开始位置 LOCATE


函数 LOCATE(s1,str) / POSITION(s1 IN str) / INSTR(str,s1) 用来查找字符串 s1 在 str 中首次出现的位置。




SELECT LOCATE('l', 'hello'), POSITION('l' IN 'hello'), INSTR('hello', 'l');
+----------------------+--------------------------+---------------------+
| LOCATE('l', 'hello') | POSITION('l' IN 'hello') | INSTR('hello', 'l') |
+----------------------+--------------------------+---------------------+
|                    3 |                        3 |                   3 |
+----------------------+--------------------------+---------------------+


13. 反转字符串 REVERSE


函数 REVERSE(s) 返回字符串 s 反转后的结果。




SELECT REVERSE('hello');
+------------------+
| REVERSE('hello') |
+------------------+
| olleh            |
+------------------+


14. 返回指定位置的字符串 ELT


函数 ELT(n, s1, s2, s3, ...) 返回字符串列表 s1, s2, s3, ... 中指定位置的成员。




SELECT ELT(3, 'a', 'b', 'c'), ELT(3, 'a', 'b');
+-----------------------+------------------+
| ELT(3, 'a', 'b', 'c') | ELT(3, 'a', 'b') |
+-----------------------+------------------+
| c                     | NULL             |
+-----------------------+------------------+


✍ 索引越界时返回 NULL 。


16. 返回字符串所在位置 FIELD 和 FIND_IN_SET


函数 FIELD(s, s1, s2, s3, ...) 返回指定的成员字符串在字符串列表 s1, s2, s3, ... 中的位置。




SELECT FIELD('b', 'a', 'b', 'c'), FIELD('b', 'a', 'c');
+---------------------------+----------------------+
| FIELD('b', 'a', 'b', 'c') | FIELD('b', 'a', 'c') |
+---------------------------+----------------------+
|                         2 |                    0 |
+---------------------------+----------------------+


函数 FIND_IN_SET 与 FIELD 的参数形式不同：FIND_IN_SET(s1, s2) 中的字符串 s2 是一个由 , 分隔的字符串列表。




SELECT FIND_IN_SET('b', 'a,b,c'), FIND_IN_SET('b', 'a,c');
+---------------------------+-------------------------+
| FIND_IN_SET('b', 'a,b,c') | FIND_IN_SET('b', 'a,c') |
+---------------------------+-------------------------+
|                         2 |                       0 |
+---------------------------+-------------------------+


✍ 当目标字符串在字符串列表中未找到时，返回的位置索引为 0 。


17. 选取字符串组合MAKE_SET


函数 MAKE_SET 使用二进制数从字符串列表中选出字符串组合。




SELECT MAKE_SET(1, 'a', 'b', 'c', 'd'),
       MAKE_SET(1 | 2, 'a', 'b', 'c', 'd'), -> MAKE_SET(1|2|4, 'a', 'b', 'c', 'd'), MAKE_SET(1|2|4|8, 'a', 'b', 'c', 'd');
+------------------------------+--------------------------------+----------------------------------+---------+
| MAKE_SET(1, 'a','b','c','d') | MAKE_SET(1|2, 'a','b','c','d') | MAKE_SET(1|2|4, 'a','b','c','d') | ；      |
+------------------------------+--------------------------------+----------------------------------+---------+
| a                            | a,b                            | a,b,c                            | a,b,c,d |
+------------------------------+--------------------------------+----------------------------------+---------+
需求：1、去除  字符串；2去除 上午sql的执行结果；3除了sql外其他的文本加上##注释。总之就是我只要描述文本和示例SQL 。