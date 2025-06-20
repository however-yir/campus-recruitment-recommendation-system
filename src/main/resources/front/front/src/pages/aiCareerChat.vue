<template>
	<div class="ai-career-page">
		<div class="section-title">AI智能求职问答</div>
		<div class="chat-card">
			<div class="tips">
				<span>可用于简历优化、面试准备、岗位选择、薪资谈判等求职问题咨询。</span>
			</div>

			<div class="chat-window" ref="chatWindow">
				<div
					v-for="(item,index) in messages"
					:key="index"
					:class="['message-item', item.role === 'user' ? 'message-user' : 'message-assistant']"
				>
					<div class="message-role">{{ item.role === 'user' ? '我' : 'AI求职助手' }}</div>
					<div class="message-content" v-html="formatContent(item.content)"></div>
				</div>
				<div class="message-loading" v-if="loading">AI 正在思考，请稍候...</div>
			</div>

			<div class="input-area">
				<el-input
					type="textarea"
					:rows="4"
					:maxlength="1000"
					show-word-limit
					placeholder="输入你的求职问题，例如：Java后端简历怎么写更容易过筛？"
					v-model="question"
				></el-input>
				<div class="action-row">
					<el-button type="primary" :loading="loading" @click="submitQuestion">发送问题</el-button>
					<el-button @click="clearHistory">清空对话</el-button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {
			question: '',
			loading: false,
			messages: [
				{
					role: 'assistant',
					content: '你好，我是你的 AI 求职助手。你可以问我：简历优化、岗位匹配、面试题准备、校招投递策略等问题。'
				}
			]
		}
	},
	methods: {
		async submitQuestion() {
			var input = this.question ? this.question.trim() : '';
			if (!input) {
				this.$message.warning('请输入问题后再发送');
				return;
			}
			if (this.loading) {
				return;
			}

			this.messages.push({
				role: 'user',
				content: input
			});
			this.question = '';
			this.loading = true;
			this.$nextTick(this.scrollToBottom);

			try {
				var res = await this.$http.post('ai/career/ask', { question: input });
				if (res.data && res.data.code === 0) {
					this.messages.push({
						role: 'assistant',
						content: res.data.answer || '暂未获得有效回复，请稍后重试。'
					});
				} else {
					var message = (res.data && res.data.msg) ? res.data.msg : 'AI服务暂时不可用';
					this.messages.push({
						role: 'assistant',
						content: '调用失败：' + message
					});
					this.$message.error(message);
				}
			} catch (e) {
				this.messages.push({
					role: 'assistant',
					content: '调用失败：网络异常或服务暂不可用，请稍后再试。'
				});
				this.$message.error('请求失败，请检查后端服务和 AI 配置');
			} finally {
				this.loading = false;
				this.$nextTick(this.scrollToBottom);
			}
		},
		clearHistory() {
			this.messages = [
				{
					role: 'assistant',
					content: '对话已清空。你可以继续咨询任意求职问题。'
				}
			];
			this.$nextTick(this.scrollToBottom);
		},
		scrollToBottom() {
			var container = this.$refs.chatWindow;
			if (container) {
				container.scrollTop = container.scrollHeight;
			}
		},
		formatContent(text) {
			return this.escapeHtml(text || '').replace(/\n/g, '<br/>');
		},
		escapeHtml(content) {
			return content
				.replace(/&/g, '&amp;')
				.replace(/</g, '&lt;')
				.replace(/>/g, '&gt;')
				.replace(/"/g, '&quot;')
				.replace(/'/g, '&#39;');
		}
	}
}
</script>

<style lang="scss" scoped>
.ai-career-page {
	width: 100%;
	padding: 20px 16%;
	margin: 0 auto;
	background: #f2f3f7;
}

.section-title {
	margin: 0 0 20px;
	color: #fff;
	text-align: center;
	background: url(http://codegen.caihongy.cn/20241119/91b3da33f957476e8c833cb4ebc67d27.png) center center/cover no-repeat;
	font-size: 30px;
	line-height: 160px;
	height: 240px;
}

.chat-card {
	background: #fff;
	border-radius: 12px;
	padding: 20px;
	box-shadow: 0 8px 20px rgba(30, 35, 90, 0.08);
}

.tips {
	font-size: 14px;
	color: #666;
	margin-bottom: 16px;
}

.chat-window {
	height: 460px;
	overflow-y: auto;
	border: 1px solid #ebeef5;
	border-radius: 8px;
	padding: 16px;
	background: #fafbfd;
}

.message-item {
	margin-bottom: 14px;
}

.message-role {
	font-size: 13px;
	color: #909399;
	margin-bottom: 6px;
}

.message-content {
	display: inline-block;
	max-width: 85%;
	padding: 10px 12px;
	line-height: 1.7;
	border-radius: 10px;
	white-space: normal;
	word-break: break-word;
}

.message-assistant .message-content {
	background: #ffffff;
	border: 1px solid #e4e7ed;
	color: #303133;
}

.message-user {
	text-align: right;
}

.message-user .message-content {
	background: #409eff;
	color: #fff;
}

.message-loading {
	font-size: 13px;
	color: #909399;
}

.input-area {
	margin-top: 16px;
}

.action-row {
	margin-top: 12px;
	display: flex;
	gap: 10px;
}
</style>
