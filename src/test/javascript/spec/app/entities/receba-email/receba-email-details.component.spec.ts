/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import RecebaEmailDetailComponent from '@/entities/receba-email/receba-email-details.vue';
import RecebaEmailClass from '@/entities/receba-email/receba-email-details.component';
import RecebaEmailService from '@/entities/receba-email/receba-email.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('RecebaEmail Management Detail Component', () => {
    let wrapper: Wrapper<RecebaEmailClass>;
    let comp: RecebaEmailClass;
    let recebaEmailServiceStub: SinonStubbedInstance<RecebaEmailService>;

    beforeEach(() => {
      recebaEmailServiceStub = sinon.createStubInstance<RecebaEmailService>(RecebaEmailService);

      wrapper = shallowMount<RecebaEmailClass>(RecebaEmailDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { recebaEmailService: () => recebaEmailServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundRecebaEmail = { id: 123 };
        recebaEmailServiceStub.find.resolves(foundRecebaEmail);

        // WHEN
        comp.retrieveRecebaEmail(123);
        await comp.$nextTick();

        // THEN
        expect(comp.recebaEmail).toBe(foundRecebaEmail);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundRecebaEmail = { id: 123 };
        recebaEmailServiceStub.find.resolves(foundRecebaEmail);

        // WHEN
        comp.beforeRouteEnter({ params: { recebaEmailId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.recebaEmail).toBe(foundRecebaEmail);
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
