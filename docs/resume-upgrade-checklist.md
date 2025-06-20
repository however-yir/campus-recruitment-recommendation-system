# Campus Recruitment Recommendation System Resume Upgrade Checklist

## 1. 功能
- [x] User-CF 推荐主链路保留
- [x] 推荐结果支持热门岗位兜底
- [x] 推荐结果增加解释字段（相似用户推荐/热门兜底）
- [ ] 内容推荐补充与混合策略融合
- [ ] 冷启动策略细化（专业/技能标签）
- [ ] 浏览收藏投递行为全量回流

## 2. 工程化
- [x] 配置外置（`application.yml` 环境变量化）
- [x] 新增 `.env.example`
- [x] 新增 `docker-compose.dev.yml`（MySQL + Redis）
- [x] 新增 CI（GitHub Actions）
- [ ] Spring Security + JWT
- [ ] Redis 热门岗位缓存正式接入

## 3. README
- [x] 增加改造清单入口
- [ ] 推荐架构图
- [ ] 冷启动与兜底策略说明
- [ ] 推荐解释示例
- [ ] 业务闭环（推荐 -> 投递 -> 反馈）说明

## 4. 测试
- [x] 推荐兜底逻辑单测
- [x] 推荐空输入边界单测
- [x] 离线评估脚本骨架（Precision/Recall/HitRate）
- [ ] 推荐服务集成测试
- [ ] 权限与异常分支测试
