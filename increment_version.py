import re

# 读取当前版本号
with open('VERSION', 'r') as f:
    version = f.read().strip()

# 解析版本号
major, minor = map(int, re.findall(r'\d+', version))

# 递增版本号
minor += 1
new_version = f"v{major}.{minor}"

# 写入新的版本号
with open('VERSION', 'w') as f:
    f.write(new_version)

print(new_version)
