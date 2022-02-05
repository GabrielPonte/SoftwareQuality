/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TipoInformacaoDetailComponent from '@/entities/tipo-informacao/tipo-informacao-details.vue';
import TipoInformacaoClass from '@/entities/tipo-informacao/tipo-informacao-details.component';
import TipoInformacaoService from '@/entities/tipo-informacao/tipo-informacao.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('TipoInformacao Management Detail Component', () => {
    let wrapper: Wrapper<TipoInformacaoClass>;
    let comp: TipoInformacaoClass;
    let tipoInformacaoServiceStub: SinonStubbedInstance<TipoInformacaoService>;

    beforeEach(() => {
      tipoInformacaoServiceStub = sinon.createStubInstance<TipoInformacaoService>(TipoInformacaoService);

      wrapper = shallowMount<TipoInformacaoClass>(TipoInformacaoDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { tipoInformacaoService: () => tipoInformacaoServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTipoInformacao = { id: 123 };
        tipoInformacaoServiceStub.find.resolves(foundTipoInformacao);

        // WHEN
        comp.retrieveTipoInformacao(123);
        await comp.$nextTick();

        // THEN
        expect(comp.tipoInformacao).toBe(foundTipoInformacao);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTipoInformacao = { id: 123 };
        tipoInformacaoServiceStub.find.resolves(foundTipoInformacao);

        // WHEN
        comp.beforeRouteEnter({ params: { tipoInformacaoId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.tipoInformacao).toBe(foundTipoInformacao);
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
