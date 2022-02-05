/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import TipoInformacaoUpdateComponent from '@/entities/tipo-informacao/tipo-informacao-update.vue';
import TipoInformacaoClass from '@/entities/tipo-informacao/tipo-informacao-update.component';
import TipoInformacaoService from '@/entities/tipo-informacao/tipo-informacao.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('TipoInformacao Management Update Component', () => {
    let wrapper: Wrapper<TipoInformacaoClass>;
    let comp: TipoInformacaoClass;
    let tipoInformacaoServiceStub: SinonStubbedInstance<TipoInformacaoService>;

    beforeEach(() => {
      tipoInformacaoServiceStub = sinon.createStubInstance<TipoInformacaoService>(TipoInformacaoService);

      wrapper = shallowMount<TipoInformacaoClass>(TipoInformacaoUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          tipoInformacaoService: () => tipoInformacaoServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.tipoInformacao = entity;
        tipoInformacaoServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(tipoInformacaoServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.tipoInformacao = entity;
        tipoInformacaoServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(tipoInformacaoServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTipoInformacao = { id: 123 };
        tipoInformacaoServiceStub.find.resolves(foundTipoInformacao);
        tipoInformacaoServiceStub.retrieve.resolves([foundTipoInformacao]);

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
