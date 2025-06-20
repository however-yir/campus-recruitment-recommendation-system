export default {
	baseUrl: 'http://localhost:8080/hire/',
	name: '/hire',
	indexNav: [
		{
			name: '企业宣讲',
			url: '/index/qiyexuanjiang',
		},
		{
			name: '招聘信息',
			url: '/index/zhaopinxinxi',
		},
		{
			name: 'AI求职问答',
			url: '/index/aiCareerChat',
		},
		{
			name: '交流论坛',
			url: '/index/forum'
		}, 
		{
			name: '公告信息',
			url: '/index/news'
		},
		{
			name: '留言反馈',
			url: '/index/messages'
		},
	],
	cateList: [
		{
			name: '招聘信息',
			refTable: 'zhiweileixing',
			refColumn: 'zhiweileixing',
		},
		{
			name: '交流论坛',
			refTable: 'forumtype',
			refColumn: 'typename',
		},
		{
			name: '公告信息',
			refTable: 'newstype',
			refColumn: 'typename',
		},
	]
}
