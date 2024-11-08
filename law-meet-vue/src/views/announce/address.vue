<template>
    <div class="app-container">
        <quill-editor v-model="content" :options="editorOption"></quill-editor>
        <el-button class="btn" type="primary" @click="submitContent">提交</el-button>
    </div>
</template>
<script>
    import { annGet, annSaveOrUpdate } from '@/api/business/cert'
    import { quillEditor } from 'vue-quill-editor'
    import 'quill/dist/quill.core.css'
    import 'quill/dist/quill.snow.css'
    import 'quill/dist/quill.bubble.css'
    import Quill from 'quill'
    import resizeImage from 'quill-image-resize-module'
    import { ImageDrop } from 'quill-image-drop-module'
    const Align = Quill.import('attributors/style/align');
    Align.whitelist = ['right', 'center', 'justify'];
    Quill.register(Align, true);
    Quill.register('modules/imageDrop', ImageDrop);
    Quill.register('modules/resizeImage ', resizeImage);

    export default {
        components: { quillEditor },
        data() {
            return {
                content:'',
                annForm: {
                    id: '',
                    type: '地址查询',
                    content: '',
                },
                editorOption: {
                    placeholder: "请输入...",
                },

            }
        },
        created() {
            this.getContent()
        },
        methods: {
            getContent() {
                annGet({ type: this.annForm.type }).then(res => {
                    this.content = res.data.data.content
                    this.annForm = res.data.data
                }).catch((err) => {
                    console.log(err)
                })
            },
            submitContent() {
                this.annForm.content = this.content
                annSaveOrUpdate(this.annForm).then(res => {
                    if (res.data.code == 200) {
                        this.$message({
                            showClose: true,
                            message: '发布成功',
                            type: 'success'
                        });
                    } else {
                        this.$message({
                            showClose: true,
                            message: res.data.message,
                            type: 'error'
                        });
                    }
                }).catch((err) => {
                    console.log(err)
                })
            }
        }
    }
</script>
<style>
    .ql-editor {
        height: 75vh;
    }

    .app-container {
        text-align: center;
    }

    .btn {
        margin-top: 2vh;
    }
</style>