<template>
	<div class="home-preview">




		<!-- 新闻资讯 -->
		<div id="animate_newsnews" class="news animate__animated">
			<div class="news_title_box">
				<span class="news_title">公告信息</span>
				<span class="news_subhead">{{'news'.toUpperCase()}}</span>
			</div>
			<!-- 样式四 -->
			<div v-if="newsList.length" class="list list4 index-pv1">
				<div @click="toDetail('newsDetail', newsList[0])" v-if="newsList.length>0" class="list-item1 animation-box">
					<div class="name line1">{{newsList[0].title}}</div>
					<div class="desc line3">{{newsList[0].introduction}}</div>
					<div class="time_item">
						<span class="icon iconfont icon-shijian21"></span>
						<span class="label">发布时间：</span>
						<span class="text">{{newsList[0].addtime}}</span>
					</div>
					<div class="publisher_item">
						<span class="icon iconfont icon-geren16"></span>
						<span class="label">发布人：</span>
						<span class="text">{{newsList[0].name}}</span>
					</div>
					<div class="like_item">
						<span class="icon iconfont icon-zan10"></span>
						<span class="label">点赞数：</span>
						<span class="text">{{newsList[0].thumbsupnum}}</span>
					</div>
					<div class="collect_item">
						<span class="icon iconfont icon-shoucang10"></span>
						<span class="label">收藏量：</span>
						<span class="text">{{newsList[0].storeupnum}}</span>
					</div>
					<div class="view_item">
						<span class="icon iconfont icon-chakan9"></span>
						<span class="label">点击量：</span>
						<span class="text">{{newsList[0].clicknum}}</span>
					</div>
				</div>
				<div @click="toDetail('newsDetail', newsList[0])" v-if="newsList.length>0" class="list-item2 animation-box">
					<img :src="baseUrl + newsList[0].picture" alt="">
				</div>
				<div @click="toDetail('newsDetail', newsList[1])" v-if="newsList.length>1" class="list-item3 animation-box">
					<div class="name line1">{{newsList[1].title}}</div>
					<div class="desc  line3">{{newsList[1].introduction}}</div>
					<div class="time_item">
						<span class="icon iconfont icon-shijian21"></span>
						<span class="label">发布时间：</span>
						<span class="text">{{newsList[1].addtime}}</span>
					</div>
					<div class="publisher_item">
						<span class="icon iconfont icon-geren16"></span>
						<span class="label">发布人：</span>
						<span class="text">{{newsList[1].name}}</span>
					</div>
					<div class="like_item">
						<span class="icon iconfont icon-zan10"></span>
						<span class="label">点赞数：</span>
						<span class="text">{{newsList[1].thumbsupnum}}</span>
					</div>
					<div class="collect_item">
						<span class="icon iconfont icon-shoucang10"></span>
						<span class="label">收藏量：</span>
						<span class="text">{{newsList[1].storeupnum}}</span>
					</div>
					<div class="view_item">
						<span class="icon iconfont icon-chakan9"></span>
						<span class="label">点击量：</span>
						<span class="text">{{newsList[1].clicknum}}</span>
					</div>
				</div>
				<div @click="toDetail('newsDetail', newsList[1])" v-if="newsList.length>1" class="list-item4 animation-box">
					<img :src="baseUrl + newsList[1].picture" alt="">
				</div>
				<div @click="toDetail('newsDetail', newsList[2])" v-if="newsList.length>2" class="list-item5 animation-box">
					<div class="name  line1">{{newsList[2].title}}</div>
					<div class="desc  line3">{{newsList[2].introduction}}</div>
					<div class="time_item">
						<span class="icon iconfont icon-shijian21"></span>
						<span class="label">发布时间：</span>
						<span class="text">{{newsList[2].addtime}}</span>
					</div>
					<div class="publisher_item">
						<span class="icon iconfont icon-geren16"></span>
						<span class="label">发布人：</span>
						<span class="text">{{newsList[2].name}}</span>
					</div>
					<div class="like_item">
						<span class="icon iconfont icon-zan10"></span>
						<span class="label">点赞数：</span>
						<span class="text">{{newsList[2].thumbsupnum}}</span>
					</div>
					<div class="collect_item">
						<span class="icon iconfont icon-shoucang10"></span>
						<span class="label">收藏量：</span>
						<span class="text">{{newsList[2].storeupnum}}</span>
					</div>
					<div class="view_item">
						<span class="icon iconfont icon-chakan9"></span>
						<span class="label">点击量：</span>
						<span class="text">{{newsList[2].clicknum}}</span>
					</div>
				</div>
				<div @click="toDetail('newsDetail', newsList[2])" v-if="newsList.length>2" class="list-item6 animation-box">
					<img :src="baseUrl + newsList[2].picture" alt="">
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('news')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 新闻资讯 -->
		<!-- 商品推荐 -->
		<div id="animate_recommendzhaopinxinxi" class="recommend animate__animated">
			<div class="recommend_title_box">
				<span class="recommend_title">招聘信息推荐</span>
				<span class="recommend_subhead">{{'zhaopinxinxi'.toUpperCase()}} RECOMMEND</span>
			</div>
			<div v-if="zhaopinxinxiRecommend.length" class="list list12 index-pv1 list12zhaopinxinxi">
				<div class="list-body">
					<div class="item list-item animation-box" v-for="item,index in zhaopinxinxiRecommend" :key="index" @click="toDetail('zhaopinxinxiDetail', item)">
						<img :name="item.id" v-if="preHttp(item.zhaopinfengmian)" :src="item.zhaopinfengmian.split(',')[0]" alt="" />
						<img :name="item.id" v-else :src="baseUrl + (item.zhaopinfengmian?item.zhaopinfengmian.split(',')[0]:'')" alt="" />
						<div class="name">{{item.qiyemingcheng}}</div>
						<div class="reason" v-if="item.reason">推荐理由：{{item.reason}}</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('zhaopinxinxi')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 商品推荐 -->
		<!-- 特价商品 -->
		<div id="animate_listqiyexuanjiang" class="lists animate__animated">
			<div class="list_title_box">
				<span class="list_title">企业宣讲展示</span>
				<span class="list_subhead">{{'qiyexuanjiang'.toUpperCase()}} SHOW</span>
			</div>
			<div v-if="qiyexuanjiangList.length" class="list list11 index-pv1">
				<div class="list-body">
					<div class="item list-item animation-box" v-for="item,index in qiyexuanjiangList" :key="index" @click="toDetail('qiyexuanjiangDetail', item)">
						<img :name="item.id" v-if="preHttp(item.fengmian)" :src="item.fengmian.split(',')[0]" alt="" />
						<img :name="item.id" v-else :src="baseUrl + (item.fengmian?item.fengmian.split(',')[0]:'')" alt="" />
						<div class="name">{{item.biaoti}}</div>
						<div class="name">{{item.qiyemingcheng}}</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('qiyexuanjiang')">
				<span class="text">更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
	

		</div>
		<!-- 特价商品 -->
	</div>
</template>

<script>
import 'animate.css'
import Swiper from "swiper";

	export default {
		//数据集合
		data() {
			return {
				baseUrl: '',
				newsList: [],
				zhaopinxinxiRecommend: [],

				qiyexuanjiangList: [],


				recommendIndex12zhaopinxinxi: 0,
				recommendList12zhaopinxinxi: [],
				recommendColumn12zhaopinxinxi: '',


			}
		},
		created() {
			this.baseUrl = this.$config.baseUrl;
			this.getNewsList();
			this.getList();
			this.zhaopinxinxiRecommendCate()
		},
		mounted() {
			window.addEventListener('scroll', this.handleScroll)
			setTimeout(()=>{
				this.handleScroll()
			},100)
			
			this.swiperChanges()
		},
		beforeDestroy() {
			window.removeEventListener('scroll', this.handleScroll)
		},
		//方法集合
		methods: {
			swiperChanges() {
				setTimeout(()=>{
				},750)
			},
			zhaopinxinxiRecommendCate(){
				this.$http.get('zhiweileixing/list',{}).then(res => {
					if (res.data.code == 0) {
						this.recommendList12zhaopinxinxi = res.data.data.list
					}
				});
				this.recommendColumn12zhaopinxinxi = 'zhiweileixing'
			},
			recommendIndexClick12(index, name) {
				this['recommendIndex12' + name] = index
				this.getList()
				
				document.querySelectorAll('.recommend .list12' + name + ' .list .item').forEach(el => {
					el.classList.remove("active")
				})
				setTimeout(() => {
					document.querySelectorAll('.recommend .list12' + name + ' .list .item').forEach(el => {
						el.classList.add("active")
					})
				}, 1);
			},

			listIndexClick11(index, name) {
				this['listIndex11' + name] = index[this['listColumn11' + name]]
				this.getList()
			},

			handleScroll() {
				let arr = [
					{id:'about',css:'animate__heartBeat'},
					{id:'system',css:'animate__bounceInRight'},
					{id:'animate_listqiyexuanjiang',css:'animate__bounceInLeft'},
					{id:'animate_recommendzhaopinxinxi',css:'animate__backInRight'},
					{id:'animate_newsnews',css:'animate__backInLeft'},
				]
			
				for (let i in arr) {
					let doc = document.getElementById(arr[i].id)
					if (doc) {
						let top = doc.offsetTop
						let win_top = window.innerHeight + window.pageYOffset
						// console.log(top,win_top)
						if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
							// console.log(doc)
							doc.classList.add(arr[i].css)
						}
					}
				}
			},
			preHttp(str) {
				return str && str.substr(0,4)=='http';
			},
			getNewsList() {
				let data = {
					page: 1,
					limit: 6,
					sort: 'addtime',
					order: 'desc'
				}
				this.$http.get('news/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.newsList = res.data.data.list;
					
					}
				});
			},
			getList() {
				let autoSortUrl = "";
				let data = {}
				autoSortUrl = "zhaopinxinxi/autoSort";
				if(localStorage.getItem('frontToken')) {
					autoSortUrl = "zhaopinxinxi/autoSort2";
				}
				data = {
					page: 1,
					limit: 8,
				}
				data['sfsh'] = '是'
				if(this.recommendIndex12zhaopinxinxi != 0){
					data.zhiweileixing = this.recommendList12zhaopinxinxi[this.recommendIndex12zhaopinxinxi - 1].zhiweileixing
				}
				this.$http.get(autoSortUrl, {params: data}).then(res => {
					if (res.data.code == 0) {
						this.zhaopinxinxiRecommend = res.data.data.list;
					}
				});
			
				data = {
					page: 1,
					limit: 8,
				}

				this.$http.get('qiyexuanjiang/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.qiyexuanjiangList = res.data.data.list;
					}
				});
			},
			toDetail(path, item) {
				this.$router.push({path: '/index/' + path, query: {id: item.id}});
			},
			moreBtn(path) {
				this.$router.push({path: '/index/' + path});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.home-preview {
		margin: 10px auto;
		flex-direction: column;
		display: flex;
		width: 100%;
		.news {
			margin: 0 auto 10px;
			background: #fff;
			width: 68%;
			order: 1;
			.news_title_box {
				margin: 30px auto;
				background: none;
				width: 200px;
				line-height: 54px;
				text-align: center;
				.news_title {
					color: #0AB8C1;
					font-weight: bold;
					font-size: 26px;
				}
				.news_subhead {
					margin: 0 0 10px;
					color: #999;
					display: none;
					width: 100%;
					font-size: 20px;
					line-height: 1.5;
					text-align: center;
				}
			}
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1.1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list4 {
				padding: 10px;
				background: #fff;
				display: flex;
				width: 100%;
				justify-content: center;
				flex-wrap: wrap;
				height: auto;
				.list-item1 {
					padding: 10px 40px;
					flex-direction: column;
					background: #F8F8F8;
					display: flex;
					width: 50%;
					justify-content: center;
					align-items: flex-start;
					height: 360px;
					order: 2;
					.name {
						margin: 0;
						overflow: hidden;
						color: #113B5A;
						font-weight: bold;
						display: -webkit-box;
						width: 100%;
						font-size: 16px;
						text-overflow: ellipsis;
						-webkit-box-orient: vertical;
						-webkit-line-clamp: 2;
						text-align: left;
					}
					.desc {
						padding: 0 0;
						margin: 20px 0;
						overflow: hidden;
						color: #526976;
						display: -webkit-box;
						width: 100%;
						font-size: 15px;
						text-overflow: ellipsis;
						-webkit-box-orient: vertical;
						-webkit-line-clamp: 2;
					}
					.time_item {
						border: 1px solid #113B5A;
						padding: 0 20px;
						.icon {
							margin: 0 2px 0 0;
							color: #113B5A;
							font-size: 12px;
							line-height: 40px;
						}
						.label {
							color: #666;
							display: none;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #113B5A;
							font-size: 12px;
							line-height: 40px;
						}
					}
					.publisher_item {
						padding: 0 10px;
						display: none;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
					.like_item {
						padding: 0 10px;
						display: none;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
					.collect_item {
						padding: 0 10px;
						display: none;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
					.view_item {
						padding: 0 10px;
						display: none;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
				}
				.list-item2 {
					background: none;
					width: 50%;
					height: 360px;
					order: 1;
					img {
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
				
				.list-item3 {
					padding: 10px 20px;
					flex-direction: column;
					background: #70CBD0;
					display: flex;
					width: 25%;
					justify-content: center;
					align-items: flex-start;
					height: 360px;
					order: 4;
					.name {
						margin: 0;
						overflow: hidden;
						color: #113B5A;
						font-weight: bold;
						display: -webkit-box;
						width: 100%;
						font-size: 16px;
						text-overflow: ellipsis;
						-webkit-box-orient: vertical;
						-webkit-line-clamp: 2;
						text-align: left;
					}
					.desc {
						padding: 0 0;
						margin: 20px 0;
						overflow: hidden;
						color: #526976;
						display: -webkit-box;
						width: 100%;
						font-size: 15px;
						text-overflow: ellipsis;
						-webkit-box-orient: vertical;
						-webkit-line-clamp: 2;
					}
					.time_item {
						border: 1px solid #113B5A;
						padding: 0 10px;
						.icon {
							margin: 0 2px 0 0;
							color: #113B5A;
							font-size: 12px;
							line-height: 40px;
						}
						.label {
							color: #666;
							display: none;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #113B5A;
							font-size: 12px;
							line-height: 40px;
						}
					}
					.publisher_item {
						padding: 0 10px;
						display: none;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
					.like_item {
						padding: 0 10px;
						display: none;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
					.collect_item {
						padding: 0 10px;
						display: none;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
					.view_item {
						padding: 0 10px;
						display: none;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
				}
				.list-item4 {
					background: #f5f5f5;
					width: 25%;
					height: 360px;
					order: 3;
					img {
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
				.list-item5 {
					padding: 10px 20px;
					flex-direction: column;
					background: #A6D1EB;
					display: flex;
					width: 25%;
					justify-content: center;
					align-items: flex-start;
					height: 360px;
					order: 6;
					.name {
						margin: 0;
						overflow: hidden;
						color: #113B5A;
						font-weight: bold;
						display: -webkit-box;
						width: 100%;
						font-size: 16px;
						text-overflow: ellipsis;
						-webkit-box-orient: vertical;
						-webkit-line-clamp: 2;
						text-align: left;
					}
					.desc {
						padding: 0 0;
						margin: 20px 0;
						overflow: hidden;
						color: #526976;
						display: -webkit-box;
						width: 100%;
						font-size: 15px;
						text-overflow: ellipsis;
						-webkit-box-orient: vertical;
						-webkit-line-clamp: 2;
					}
					.time_item {
						border: 1px solid #113B5A;
						padding: 0 10px;
						.icon {
							margin: 0 2px 0 0;
							color: #113B5A;
							font-size: 12px;
							line-height: 40px;
						}
						.label {
							color: #666;
							display: none;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #113B5A;
							font-size: 12px;
							line-height: 40px;
						}
					}
					.publisher_item {
						padding: 0 10px;
						display: none;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
					.like_item {
						padding: 0 10px;
						display: none;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
					.collect_item {
						padding: 0 10px;
						display: none;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
					.view_item {
						padding: 0 10px;
						display: none;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
				}
				.list-item6 {
					background: #f5f5f5;
					width: 25%;
					height: 360px;
					order: 5;
					img {
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
			}
			.moreBtn {
				border: 0;
				cursor: pointer;
				margin: 10px auto;
				background: none;
				display: block;
				width: 80px;
				line-height: 32px;
				text-align: center;
				.text {
					color: #9E9E9E;
					font-size: 14px;
				}
				.icon {
					color: #9E9E9E;
					font-size: 14px;
				}
			}
		}
		.recommend {
			margin: 0 auto 10px;
			background: #fff;
			width: 68%;
			position: relative;
			order: 2;
			.recommend_title_box {
				box-shadow: 0 4px 8px rgba(0,0,0,.2);
				padding: 0 50px;
				margin: 10px auto 0;
				background: #fff;
				width: 100%;
				line-height: 70px;
				text-align: left;
				height: 70px;
				.recommend_title {
					color: #0AB8C1;
					font-weight: bold;
					font-size: 26px;
				}
				.recommend_subhead {
					margin: 0 0 10px;
					color: #999;
					display: none;
					width: 100%;
					font-size: 20px;
					line-height: 1.5;
					text-align: center;
				}
			}
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				box-shadow: 0 4px 5px rgba(0,0,0,.3);
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 10px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1.1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list12 {
				padding: 0 0 10px;
				background: #fff;
				width: 100%;
				height: auto;
				.tab_view {
					box-shadow: 0 4px 8px rgba(0,0,0,.3);
					margin: 0 0 20px;
					display: flex;
					width: 100%;
					justify-content: center;
					height: auto;
					.tab {
						cursor: pointer;
						border: 1px solid #0AB8C1;
						padding: 0 0;
						margin: 0 0;
						color: #0AB8C1;
						flex: 1;
						display: flex;
						width: auto;
						font-size: 16px;
						line-height: 44px;
						justify-content: center;
						align-items: center;
						img {
							margin: 0 5px 0 0;
							object-fit: cover;
							display: none;
							width: 34px;
							height: 34px;
						}
						.text {
							color: inherit;
							font-size: 14px;
						}
					}
					.tab:hover {
						color: #fff;
						background: #E72C59;
						border-color: #E72C59;
					}
					.tab.active {
						color: #fff;
						background: #E72C59;
						border-color: #E72C59;
					}
					.more_btn {
						cursor: pointer;
						padding: 5px 10px;
						margin: 0 10px;
						color: #666;
						background: #fff;
						display: none;
						line-height: 44px;
						align-items: center;
						.text {
							color: inherit;
							font-size: 14px;
						}
						.icon {
							margin: 0 0 0 2px;
							color: inherit;
							font-size: 14px;
						}
					}
					.more_btn:hover {
						color: red;
					}
					.tab.active {
						animation-name: mymove;
					
						&:nth-of-type(1) {
							animation-duration: 1s;
						}
						&:nth-of-type(2) {
							animation-duration: 1.2s;
						}
						&:nth-of-type(3) {
							animation-duration: 1.4s;
						}
						&:nth-of-type(4) {
							animation-duration: 1.6s;
						}
						&:nth-of-type(5) {
							animation-duration: 1.8s;
						}
						&:nth-of-type(6) {
							animation-duration: 2s;
						}
					}
					
					@keyframes mymove {
						from {top: 320px;}
						to {top: 0;}
					}
				}
				.list-body {
					padding: 20px 10px;
					overflow: hidden;
					flex: 1;
					background: #fff;
					display: flex;
					justify-content: flex-start;
					flex-wrap: wrap;
					height: auto;
					.list-item {
						border: 2px solid #F3F3F3;
						padding: 30px;
						box-shadow: 0;
						margin: 0 1% 20px;
						width: 23%;
						position: relative;
						height: auto;
						img {
							object-fit: cover;
							display: block;
							width: 100%;
							height: 280px;
						}
						.name {
							border: 1px solid #F3F3F3;
							padding: 10px 0;
							color: #000000;
							font-size: 20px;
							line-height: 32px;
							border-width: 0 0 1px;
						}
						.reason {
							margin: 10px 0 0;
							border-radius: 4px;
							padding: 6px 8px;
							background: #0AB8C112;
							color: #0A7278;
							font-size: 12px;
							line-height: 18px;
						}
						.price {
							padding: 10px 0;
							color: #E72C59;
							font-size: 16px;
							line-height: 1.5;
						}
					}
					.list-item.active {
						animation-name: mymove;
					
						&:nth-of-type(1) {
							animation-duration: 1s;
						}
						&:nth-of-type(2) {
							animation-duration: 1.2s;
						}
						&:nth-of-type(3) {
							animation-duration: 1.4s;
						}
						&:nth-of-type(4) {
							animation-duration: 1.6s;
						}
						&:nth-of-type(5) {
							animation-duration: 1.8s;
						}
						&:nth-of-type(6) {
							animation-duration: 2s;
						}
					}
					
					@keyframes mymove {
						from {top: 320px;}
						to {top: 0;}
					}
				}
			}
			.moreBtn {
				border: 0;
				cursor: pointer;
				margin: 10px auto;
				top: 0;
				background: none;
				display: block;
				width: auto;
				line-height: 70px;
				position: absolute;
				right: 50px;
				text-align: center;
				.text {
					color: #9E9E9E;
					font-size: 14px;
				}
				.icon {
					color: #9E9E9E;
					font-size: 14px;
				}
			}
		}
		.lists {
			padding: 30px 16% 50px;
			margin: 0 0 ;
			background: #F2F3F7;
			width: 100%;
			position: relative;
			order: 4;
			.list_title_box {
				box-shadow: 0 4px 8px rgba(0,0,0,.2);
				padding: 0 50px;
				margin: 10px auto 0;
				background: #fff;
				width: 100%;
				line-height: 70px;
				text-align: left;
				height: 70px;
				.list_title {
					color: #0AB8C1;
					font-weight: bold;
					font-size: 26px;
				}
				.list_subhead {
					margin: 0 0 10px;
					color: #999;
					display: none;
					width: 100%;
					font-size: 20px;
					line-height: 1.5;
					text-align: center;
				}
			}
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 10px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1.1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list11 {
				padding: 0;
				margin: 30px 0 0;
				background: none;
				display: flex;
				width: 100%;
				justify-content: space-between;
				align-items: flex-start;
				height: auto;
				.tab_view {
					margin: 0px 30px 0 0;
					flex-direction: column;
					background: #fff;
					display: flex;
					width: 220px;
					height: auto;
					.tab {
						cursor: pointer;
						padding: 10px 30px;
						margin: 0;
						display: flex;
						width: 100%;
						border-color: #efefef;
						border-width: 0;
						align-items: center;
						border-style: solid;
						height: auto;
						.icon {
							margin: 0 5px 0 0;
							color: #333;
							display: none;
							font-size: 16px;
							line-height: 44px;
						}
						img {
							border-radius: 50%;
							margin: 0 10px 0 0;
							object-fit: cover;
							display: inline-block;
							width: 44px;
							height: 44px;
						}
						.text {
							color: #0AB8C1;
							font-size: 14px;
							line-height: 44px;
						}
					}
					.more_btn {
						cursor: pointer;
						padding: 0 20px;
						margin: 0;
						display: none;
						width: 100%;
						border-color: #efefef;
						border-width: 0 0 1px 0;
						border-style: solid;
						height: auto;
						.icon {
							margin: 0 5px 0 0;
							color: #333;
							font-size: 16px;
							line-height: 44px;
						}
						.text {
							color: #333;
							font-size: 16px;
							line-height: 44px;
						}
					}
				}
				.list-body {
					flex: 1;
					background: #fff;
					display: flex;
					justify-content: flex-start;
					flex-wrap: wrap;
					height: auto;
					.list-item {
						padding: 20px 10px 10px;
						width: 25%;
						position: relative;
						height: auto;
						img {
							object-fit: cover;
							display: block;
							width: 100%;
							height: 300px;
						}
						.name {
							padding: 0 10px;
							color: #000;
							font-size: 18px;
							line-height: 2;
						}
						.price {
							padding: 10px;
							color: #0AB8C1;
							font-size: 13px;
							line-height: 1.5;
						}
					}
				}
			}
			.moreBtn {
				border: 0;
				cursor: pointer;
				margin: 10px auto;
				top: 20px;
				background: none;
				display: block;
				width: auto;
				line-height: 70px;
				position: absolute;
				right: calc(16% + 50px);
				text-align: center;
				.text {
					color: #9E9E9E;
					font-size: 14px;
				}
				.icon {
					color: #9E9E9E;
					font-size: 14px;
				}
			}
		}
	}
</style>
