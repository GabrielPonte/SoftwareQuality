/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import PostoSaudeDetailComponent from '@/entities/posto-saude/posto-saude-details.vue';
import PostoSaudeClass from '@/entities/posto-saude/posto-saude-details.component';
import PostoSaudeService from '@/entities/posto-saude/posto-saude.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('PostoSaude Management Detail Component', () => {
    let wrapper: Wrapper<PostoSaudeClass>;
    let comp: PostoSaudeClass;
    let postoSaudeServiceStub: SinonStubbedInstance<PostoSaudeService>;

    beforeEach(() => {
      postoSaudeServiceStub = sinon.createStubInstance<PostoSaudeService>(PostoSaudeService);

      wrapper = shallowMount<PostoSaudeClass>(PostoSaudeDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { postoSaudeService: () => postoSaudeServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundPostoSaude = { id: 123 };
        postoSaudeServiceStub.find.resolves(foundPostoSaude);

        // WHEN
        comp.retrievePostoSaude(123);
        await comp.$nextTick();

        // THEN
        expect(comp.postoSaude).toBe(foundPostoSaude);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundPostoSaude = { id: 123 };
        postoSaudeServiceStub.find.resolves(foundPostoSaude);

        // WHEN
        comp.beforeRouteEnter({ params: { postoSaudeId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.postoSaude).toBe(foundPostoSaude);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
