import VueRouter from 'vue-router'
//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Messages from '../pages/messages/list'
import Forum from '../pages/forum/list'
import ForumAdd from '../pages/forum/add'
import ForumDetail from '../pages/forum/detail'
import MyForumList from '../pages/forum/myForumList'
import Storeup from '../pages/storeup/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import payList from '../pages/pay'
import aiCareerChat from '../pages/aiCareerChat'

import yonghuList from '../pages/yonghu/list'
import yonghuDetail from '../pages/yonghu/detail'
import yonghuAdd from '../pages/yonghu/add'
import qiyexinxiList from '../pages/qiyexinxi/list'
import qiyexinxiDetail from '../pages/qiyexinxi/detail'
import qiyexinxiAdd from '../pages/qiyexinxi/add'
import qiyexuanjiangList from '../pages/qiyexuanjiang/list'
import qiyexuanjiangDetail from '../pages/qiyexuanjiang/detail'
import qiyexuanjiangAdd from '../pages/qiyexuanjiang/add'
import zhiweileixingList from '../pages/zhiweileixing/list'
import zhiweileixingDetail from '../pages/zhiweileixing/detail'
import zhiweileixingAdd from '../pages/zhiweileixing/add'
import zhaopinxinxiList from '../pages/zhaopinxinxi/list'
import zhaopinxinxiDetail from '../pages/zhaopinxinxi/detail'
import zhaopinxinxiAdd from '../pages/zhaopinxinxi/add'
import qiuzhixinxiList from '../pages/qiuzhixinxi/list'
import qiuzhixinxiDetail from '../pages/qiuzhixinxi/detail'
import qiuzhixinxiAdd from '../pages/qiuzhixinxi/add'
import zhaopinjieguoList from '../pages/zhaopinjieguo/list'
import zhaopinjieguoDetail from '../pages/zhaopinjieguo/detail'
import zhaopinjieguoAdd from '../pages/zhaopinjieguo/add'
import qiuzhijianliList from '../pages/qiuzhijianli/list'
import qiuzhijianliDetail from '../pages/qiuzhijianli/detail'
import qiuzhijianliAdd from '../pages/qiuzhijianli/add'
import yingyaozhaopinList from '../pages/yingyaozhaopin/list'
import yingyaozhaopinDetail from '../pages/yingyaozhaopin/detail'
import yingyaozhaopinAdd from '../pages/yingyaozhaopin/add'
import forumtypeList from '../pages/forumtype/list'
import forumtypeDetail from '../pages/forumtype/detail'
import forumtypeAdd from '../pages/forumtype/add'
import forumreportList from '../pages/forumreport/list'
import forumreportDetail from '../pages/forumreport/detail'
import forumreportAdd from '../pages/forumreport/add'
import newstypeList from '../pages/newstype/list'
import newstypeDetail from '../pages/newstype/detail'
import newstypeAdd from '../pages/newstype/add'
import discusszhaopinxinxiList from '../pages/discusszhaopinxinxi/list'
import discusszhaopinxinxiDetail from '../pages/discusszhaopinxinxi/detail'
import discusszhaopinxinxiAdd from '../pages/discusszhaopinxinxi/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'messages',
					component: Messages
				},
				{
					path: 'aiCareerChat',
					component: aiCareerChat
				},
				{
					path: 'forum',
					component: Forum
				},
				{
					path: 'forumAdd',
					component: ForumAdd
				},
				{
					path: 'forumDetail',
					component: ForumDetail
				},
				{
					path: 'myForumList',
					component: MyForumList
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'yonghu',
					component: yonghuList
				},
				{
					path: 'yonghuDetail',
					component: yonghuDetail
				},
				{
					path: 'yonghuAdd',
					component: yonghuAdd
				},
				{
					path: 'qiyexinxi',
					component: qiyexinxiList
				},
				{
					path: 'qiyexinxiDetail',
					component: qiyexinxiDetail
				},
				{
					path: 'qiyexinxiAdd',
					component: qiyexinxiAdd
				},
				{
					path: 'qiyexuanjiang',
					component: qiyexuanjiangList
				},
				{
					path: 'qiyexuanjiangDetail',
					component: qiyexuanjiangDetail
				},
				{
					path: 'qiyexuanjiangAdd',
					component: qiyexuanjiangAdd
				},
				{
					path: 'zhiweileixing',
					component: zhiweileixingList
				},
				{
					path: 'zhiweileixingDetail',
					component: zhiweileixingDetail
				},
				{
					path: 'zhiweileixingAdd',
					component: zhiweileixingAdd
				},
				{
					path: 'zhaopinxinxi',
					component: zhaopinxinxiList
				},
				{
					path: 'zhaopinxinxiDetail',
					component: zhaopinxinxiDetail
				},
				{
					path: 'zhaopinxinxiAdd',
					component: zhaopinxinxiAdd
				},
				{
					path: 'qiuzhixinxi',
					component: qiuzhixinxiList
				},
				{
					path: 'qiuzhixinxiDetail',
					component: qiuzhixinxiDetail
				},
				{
					path: 'qiuzhixinxiAdd',
					component: qiuzhixinxiAdd
				},
				{
					path: 'zhaopinjieguo',
					component: zhaopinjieguoList
				},
				{
					path: 'zhaopinjieguoDetail',
					component: zhaopinjieguoDetail
				},
				{
					path: 'zhaopinjieguoAdd',
					component: zhaopinjieguoAdd
				},
				{
					path: 'qiuzhijianli',
					component: qiuzhijianliList
				},
				{
					path: 'qiuzhijianliDetail',
					component: qiuzhijianliDetail
				},
				{
					path: 'qiuzhijianliAdd',
					component: qiuzhijianliAdd
				},
				{
					path: 'yingyaozhaopin',
					component: yingyaozhaopinList
				},
				{
					path: 'yingyaozhaopinDetail',
					component: yingyaozhaopinDetail
				},
				{
					path: 'yingyaozhaopinAdd',
					component: yingyaozhaopinAdd
				},
				{
					path: 'forumtype',
					component: forumtypeList
				},
				{
					path: 'forumtypeDetail',
					component: forumtypeDetail
				},
				{
					path: 'forumtypeAdd',
					component: forumtypeAdd
				},
				{
					path: 'forumreport',
					component: forumreportList
				},
				{
					path: 'forumreportDetail',
					component: forumreportDetail
				},
				{
					path: 'forumreportAdd',
					component: forumreportAdd
				},
				{
					path: 'newstype',
					component: newstypeList
				},
				{
					path: 'newstypeDetail',
					component: newstypeDetail
				},
				{
					path: 'newstypeAdd',
					component: newstypeAdd
				},
				{
					path: 'discusszhaopinxinxi',
					component: discusszhaopinxinxiList
				},
				{
					path: 'discusszhaopinxinxiDetail',
					component: discusszhaopinxinxiDetail
				},
				{
					path: 'discusszhaopinxinxiAdd',
					component: discusszhaopinxinxiAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
