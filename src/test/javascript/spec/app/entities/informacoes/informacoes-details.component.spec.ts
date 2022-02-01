/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import InformacoesDetailComponent from '@/entities/informacoes/informacoes-details.vue';
import InformacoesClass from '@/entities/informacoes/informacoes-details.component';
import InformacoesService from '@/entities/informacoes/informacoes.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Informacoes Management Detail Component', () => {
    let wrapper: Wrapper<InformacoesClass>;
    let comp: InformacoesClass;
    let informacoesServiceStub: SinonStubbedInstance<InformacoesService>;

    beforeEach(() => {
      informacoesServiceStub = sinon.createStubInstance<InformacoesService>(InformacoesService);

      wrapper = shallowMount<InformacoesClass>(InformacoesDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { informacoesService: () => informacoesServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundInformacoes = { id: 123 };
        informacoesServiceStub.find.resolves(foundInformacoes);

        // WHEN
        comp.retrieveInformacoes(123);
        await comp.$nextTick();

        // THEN
        expect(comp.informacoes).toBe(foundInformacoes);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundInformacoes = { id: 123 };
        informacoesServiceStub.find.resolves(foundInformacoes);

        // WHEN
        comp.beforeRouteEnter({ params: { informacoesId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.informacoes).toBe(foundInformacoes);
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
