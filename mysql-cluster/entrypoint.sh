# 遍历/entrypoint-initdb.d/文件中的sh文件，sql文件，sql.gz压缩包
# 随后按照sh>sql>sql.gz的顺序依次执行。

echo
     for f in /entrypoint-initdb.d/*; do
         case "$f" in
              *.sh)     echo "$0: running $f"; . "$f" ;;
              *.sql)    echo "$0: running $f"; "${mysql[@]}" < "$f"; echo ;;
              *.sql.gz) echo "$0: running $f"; gunzip -c "$f" | "${mysql[@]}"; echo ;;
              *)        echo "$0: ignoring $f" ;;
         esac
         echo
done
